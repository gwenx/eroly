package com.eroly.util.cacheUpdate;

import redis.clients.jedis.Jedis;

public class JedisTool {
	private JedisClient jedisClient;

	public Jedis getJedis() {
		return jedisClient.getClient();
	}

	public void setJedisClient(JedisClient jedisClient) {
		this.jedisClient = jedisClient;
	}
}
