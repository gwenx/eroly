package com.eroly.util.cacheUpdate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class SingleJedisClient implements JedisClient {
	private static Logger logger = LoggerFactory.getLogger(SingleJedisClient.class);
	private JedisPool jedisPool;
	public JedisPool getJedisPool() {
		return jedisPool;
	}
	public void setJedisPool(JedisPool jedisPool) {
		this.jedisPool = jedisPool;
	}
	public Jedis getClient() {
		return jedisPool.getResource();
	}
	public void distory(){
		logger.info("SingleJedisClient销毁jedisPool");
		if(jedisPool!=null&&!jedisPool.isClosed()){
			jedisPool.close();
		}
	}
}