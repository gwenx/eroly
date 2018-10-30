package com.eroly.util.cacheUpdate;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.eroly.util.DateUtil;
import com.eroly.util.SerializableUtil;
import com.eroly.util.StringUtil;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class RedisTemplate {
	private Logger logger = LoggerFactory.getLogger(RedisTemplate.class);
	@Autowired
	private JedisTool jedisTool;
	private final static String NameSpaceKey="namespacekey";
	private final static String NameSplit="#";
	public RedisTemplate() {
	}
	public RedisTemplate(JedisTool jedisTool) {
		this.jedisTool = jedisTool;
	}

	public static void main(String[] args) {
		JedisPool jedisPool = new JedisPool("127.0.0.1",6379);
		SingleJedisClient client = new SingleJedisClient();
		client.setJedisPool(jedisPool);
		JedisTool jedisTool = new JedisTool();
		jedisTool.setJedisClient(client);
		RedisTemplate redisTemplate = new RedisTemplate(jedisTool);
		
		redisTemplate.setObject("myname", "hhhhh");
		String object = (String) redisTemplate.getObject("myname");
		System.out.println(object);
		Map<String, String> map = new HashMap<String, String>();
		map.put("a", "三生三世");
		map.put("b", "dadad");
		map.put("c", "到底是啥多");
		redisTemplate.setObject("map", map);
		redisTemplate.setObject("hisMap", "map", map);
		Map<String,String> mymap = (HashMap<String,String>) redisTemplate.getObject("hisMap", "map");
		System.out.println(mymap);
		for (String key : mymap.keySet()) {
			System.out.println(key + "----"+mymap.get(key));
		}
		/*Date parseDate = DateUtil.parseDate("20181001000000", "yyyyMMddHHmmss");
		System.out.println(parseDate);
		System.out.println(parseDate.getTime());
		System.out.println(getSpaceValue());*/
	}
	public <T> T execute(RedisCallback<T> redisCallback,T value){
		if(jedisTool==null){
			logger.info("jedisTool is null");
			return value;
		}
		Jedis jedis = null;
		try {
			jedis = jedisTool.getJedis();
		} catch (Exception e) {
			logger.info("getJedis error",e);
		}
		if(null==jedis){
			logger.info("jedis is null");
			return value;
		}
		try {
			T result = redisCallback.execute(jedis);
			return result == null ? value : result;
		} catch (Exception e) {
			logger.info("redisCallback.execute error",e);
			return value;
		}finally {
			if(jedis!=null){
				jedis.close();
			}
		}
	}
	/**
	 * 存入Redis一个键值，默认过期时间一个月
	 * @param key
	 * @param value
	 * @return
	 */
	public String setObject(final String key ,final Object value){
		return setObject(key, value, 60*60*24*30);
	}
	/**
	 * 存入Redis一个键值，过期时间自定义
	 * @param key
	 * @param value
	 * @return
	 */
	public String setObject(final String key ,final Object value,final int seconds){
		if(value == null){
			return null;
		}
		if(!(value instanceof Serializable)){
			logger.error("不能存入{}；对象必须是可序列化的",key,new Throwable(key));
			return null;
		}
		String val = this.execute(new RedisCallback<String>() {
			byte[] buf = SerializableUtil.serializable(value);
			public String execute(Jedis jedis) {
				try {
					byte[] keyBuf = key.getBytes("UTF-8");
					String result = jedis.set(keyBuf, buf);
					if (seconds > 0) {
						jedis.set(keyBuf, buf);
						jedis.expire(keyBuf, seconds);
					}
					return result;
				} catch (Exception e) {
					return null;
				}
			}
		}, null);
		return val;
	}
	/**
	 * 将带nameSpace的键值存入Redis，过期时间默认一个月
	 * @param nameSpace
	 * @param key
	 * @param value
	 * @return
	 */
	public String setObject(final String nameSpace,final String key ,final Object value){
		return setObject(nameSpace,key, value, 60*60*24*30);
	}
	/**
	 * 将带nameSpace的键值存入Redis，过期时间自定义
	 * @param nameSpace
	 * @param key
	 * @param value
	 * @param seconds 过期时间
	 * @return
	 */
	public String setObject(final String nameSpace,final String key ,final Object value,final int seconds){
		if(value == null){
			return null;
		}
		if(!(value instanceof Serializable)){
			logger.error("不能存入{}；对象必须是可序列化的",key,new Throwable(key));
			return null;
		}
		String val = this.execute(new RedisCallback<String>() {
			byte[] buf = SerializableUtil.serializable(value);
			public String execute(Jedis jedis) {
				try {
					String spaceValue = jedis.hget(NameSpaceKey, nameSpace);
					if(spaceValue==null){
						spaceValue = getSpaceValue();
						jedis.hset(NameSpaceKey, nameSpace, spaceValue);
					}
					byte[] keyBuf = (nameSpace+NameSplit+spaceValue+NameSplit+key).getBytes("UTF-8");
					String result = jedis.set(keyBuf, buf);
					if (seconds > 0) {
						jedis.set(keyBuf, buf);
						jedis.expire(keyBuf, seconds);
					}
					return result;
				} catch (Exception e) {
					return null;
				}
			}
		}, null);
		return val;
	}
	private static String getSpaceValue() {
		return StringUtil.getNum62(System.currentTimeMillis()-1538323200000L);
	}
	/**
	 * 从Redis中获取一个值
	 * @param key
	 * @return
	 */
	public Object getObject(final String key){
		if(key == null){
			return null;
		}
		Object obj = this.execute(new RedisCallback<Object>() {
			public Object execute(Jedis jedis) {
				try {
					byte[] keyBuf = key.getBytes("UTF-8");
					byte[] bs = jedis.get(keyBuf);
					return SerializableUtil.unSerializable(bs);
				} catch (Exception e) {
					return null;
				}
			}
		}, null);
		return obj;
	}
	/**
	 * 从带命名空间的Redis中获取一个值
	 * @param key
	 * @return
	 */
	public Object getObject(final String nameSpace, final String key){
		if(key == null){
			return null;
		}
		Object obj = this.execute(new RedisCallback<Object>() {
			public Object execute(Jedis jedis) {
				try {
					String spaceValue = jedis.hget(NameSpaceKey, nameSpace);
					if(StringUtil.isEmpty(spaceValue)){
						return null;
					}
					byte[] keyBuf = (nameSpace + NameSplit + spaceValue + NameSplit +key).getBytes("UTF-8");
					byte[] bs = jedis.get(keyBuf);
					return SerializableUtil.unSerializable(bs);
				} catch (Exception e) {
					return null;
				}
			}
		}, null);
		return obj;
	}
}
