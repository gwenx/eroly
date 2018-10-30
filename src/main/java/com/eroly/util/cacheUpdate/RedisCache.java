package com.eroly.util.cacheUpdate;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.concurrent.Callable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.support.SimpleValueWrapper;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.eroly.util.StringUtil;

@Component
public class RedisCache implements Cache {
	private static Logger logger = LoggerFactory.getLogger(RedisCache.class);
	@Autowired
	private RedisTemplate<String, Object> redisTemplate;
	private String name;
	/**
	 * 获取对应key的value Object类型
	 * @param key 键
	 * @return
	 */
	public Object getRedisValue(Object key) {
		final String keyf = (String) key;
		return get(keyf);
//		ValueWrapper valueWrapper = get(key);
//		if(valueWrapper!=null) {
//			return valueWrapper.get();
//		}else {
//			return null;
//		}
	}
	public Object get(final String keyf) {
		logger.info("--------RedisCache.get start----");
		logger.info("--------Redis key:{}----",keyf);
		Object object = null;
		object = redisTemplate.execute(new RedisCallback<Object>() {
			public Object doInRedis(RedisConnection connection) throws DataAccessException {
				byte[] key = keyf.getBytes();
				byte[] value = connection.get(key);
				if (value == null) {
					return null;
				}
				return toObject(value);
			}
		});
		logger.info("--------RedisCache.get end----");
		return object;
	}
	public ValueWrapper get(Object key) {
		logger.info("--------RedisCache.get start----");
		logger.info("--------Redis key:{}----",key);
		final String keyf = key.toString();
		Object object = null;
		object = redisTemplate.execute(new RedisCallback<Object>() {
			public Object doInRedis(RedisConnection connection) throws DataAccessException {
				byte[] key = keyf.getBytes();
				byte[] value = connection.get(key);
				if (value == null) {
					return null;
				}
				return toObject(value);
			}
		});
		logger.info("--------RedisCache.get end----");
		return (object != null ? new SimpleValueWrapper(object) : null);
	}
	/**
	 * key对应的value放入缓存
	 * @param key
	 * @param value
	 */
	public void putObject(String key, Object value){
		if(StringUtil.isNotEmpty(key) && null != value) {
			put(key, value);
		}
	}
	/**
	 * key对应的value放入缓存
	 */
	public void put(Object key, Object value) {
		logger.info("--------RedisCache.put start----");
		logger.info("--------Redis key:{}----",key);
		final String keyf = key.toString();
		final Object valuef = value;
		final long liveTime = 86400;
		redisTemplate.execute(new RedisCallback<Long>() {
			public Long doInRedis(RedisConnection connection) throws DataAccessException {
				byte[] keyb = keyf.getBytes();
				byte[] valueb = toByteArray(valuef);
				connection.set(keyb, valueb);
				if (liveTime > 0) {
					connection.expire(keyb, liveTime);
				}
				return 1L;
			}
		});
		logger.info("--------RedisCache.put end----");
	}
	/**
	 * 对象转换为二进制数组
	 * @param obj
	 * @return
	 */
	private byte[] toByteArray(Object obj) {
		byte[] bytes = null;
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		try {
			ObjectOutputStream oos = new ObjectOutputStream(bos);
			oos.writeObject(obj);
			oos.flush();
			bytes = bos.toByteArray();
			oos.close();
			bos.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return bytes;
	}
	/**
	 * 二进制数组转化为object对象
	 * @param bytes
	 * @return
	 */
	private Object toObject(byte[] bytes) {
		Object obj = null;
		try {
			ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
			ObjectInputStream ois = new ObjectInputStream(bis);
			obj = ois.readObject();
			ois.close();
			bis.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		}
		return obj;
	}
	/**
	 * 删除key对应的value
	 */
	public void evict(Object key) {
		System.out.println("del key");
		final String keyf = key.toString();
		redisTemplate.execute(new RedisCallback<Long>() {
			public Long doInRedis(RedisConnection connection) throws DataAccessException {
				return connection.del(keyf.getBytes());
			}
		});
	}
	/**
	 * 清空缓存
	 */
	public void clear() {
		System.out.println("clear key");
		redisTemplate.execute(new RedisCallback<String>() {
			public String doInRedis(RedisConnection connection) throws DataAccessException {
				connection.flushDb();
				return "ok";
			}
		});
	}
	public RedisTemplate<String, Object> getRedisTemplate() {
		return redisTemplate;
	}

	public void setRedisTemplate(RedisTemplate<String, Object> redisTemplate) {
		this.redisTemplate = redisTemplate;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		// TODO Auto-generated method stub
		return this.name;
	}
	public Object getNativeCache() {
		// TODO Auto-generated method stub
		return this.redisTemplate;
	}
	public <T> T get(Object key, Class<T> type) {
		// TODO Auto-generated method stub
		return null;
	}
	public ValueWrapper putIfAbsent(Object key, Object value) {
		// TODO Auto-generated method stub
		return null;
	}
	public <T> T get(Object arg0, Callable<T> arg1) {
		// TODO Auto-generated method stub
		return null;
	}
}