package com.eroly.util.cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
public abstract class CacheBase<K, V> {
	@Autowired(required=true) 
    protected RedisTemplate<K, V> redisTemplate;
}
