package com.eroly.util.cacheUpdate;

import com.eroly.util.SpringTools;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * 发布订阅的管理器
 * 【执行的操作：
 * 1.将所有需要刷新加载的Service类（实现ICacheUpdate接口）添加到RedisMsgPubSub的updates中
 * 2.启动线程订阅pubsub_config频道，每隔五秒订阅一次（避免订阅到一次消息后结束订阅）】
 */
@Component
public class PubSubManager extends Thread{
    private static Logger logger = LoggerFactory.getLogger(PubSubManager.class);

    public static Jedis jedis;
    RedisMsgPubSub msgPubSub = new RedisMsgPubSub();
    public static final String PUNSUB_CONFIG = "pubsub_config";//频道

    public boolean addListener(String key, ICacheUpdate listener){
        return msgPubSub.addListener(key,listener);
    }

    public void run(){
        while (true){
            try {
                JedisPool jedisPool = SpringTools.getBean("jedisPool", JedisPool.class);
                if(jedisPool!=null){
                    jedis = jedisPool.getResource();
                    if(jedis!=null){
                        jedis.subscribe(msgPubSub,PUNSUB_CONFIG);
                    }
                }
            } catch (Exception e) {
                logger.error("redis connect error!");
            } finally {
                if(jedis!=null){
                    jedis.close();
                }
            }
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                logger.error("InterruptedException in redis sleep!");
            }
        }
    }
}
