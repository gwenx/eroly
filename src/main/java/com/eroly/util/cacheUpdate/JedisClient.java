package com.eroly.util.cacheUpdate;

import redis.clients.jedis.Jedis;

public interface JedisClient {
	public Jedis getClient();
}
