package com.eroly.util.cacheUpdate;

import redis.clients.jedis.Jedis;

public interface RedisCallback<T> {
	public <T> T execute(Jedis jedis);
}
