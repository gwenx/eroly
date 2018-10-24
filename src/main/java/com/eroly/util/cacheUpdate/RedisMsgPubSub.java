package com.eroly.util.cacheUpdate;

import java.util.HashMap;
import java.util.Map;

import com.eroly.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.JedisPubSub;

/**
 * Redis发布订阅的扩展类
 * 作用：1、统一管理ICacheUpdate，把所有实现ICacheUpdate接口的类添加到updates容器
 * 2、重写onMessage方法，订阅到消息后进行刷新缓存的操作
 */
public class RedisMsgPubSub extends JedisPubSub {
    private static Logger logger = LoggerFactory.getLogger(RedisMsgPubSub.class);

	private Map<String , ICacheUpdate> updates = new HashMap<String , ICacheUpdate>();
	//由updates统一管理ICacheUpdate
	public boolean addListener(String key , ICacheUpdate update) {
		if(update == null) {
			return false;
		}
		updates.put(key, update);
		return true;
	}
	/**
     * 订阅频道收到的消息
     */
    @Override  
    public void onMessage(String channel, String message) {
        logger.info("RedisMsgPubSub onMessage channel:{},message :{}" ,channel, message);
        ICacheUpdate updater = null;
        if(StringUtil.isNotEmpty(message)) {
        	updater = updates.get(message);
        }
        if(updater!=null){
            updater.update();
        }
    }
    /**
     * 取消订阅频道
     */
    @Override  
    public void onUnsubscribe(String channel, int subscribedChannels) {  
        logger.info("RedisMsgPubSub onMessage channel:{},当前订阅数量 :{}" ,channel, subscribedChannels);
    }  
    /**
     * 订阅频道
     */
    @Override  
    public void onSubscribe(String channel, int subscribedChannels) {  
        logger.info("RedisMsgPubSub onMessage channel:{},当前订阅数量 :{}" ,channel, subscribedChannels);
    }  
	@Override  
    public void unsubscribe() {  
        super.unsubscribe();  
    }  
  
    @Override  
    public void unsubscribe(String... channels) {  
        super.unsubscribe(channels);  
    }  
  
    @Override  
    public void subscribe(String... channels) {  
        super.subscribe(channels);  
    }  
  
    @Override  
    public void psubscribe(String... patterns) {  
        super.psubscribe(patterns);  
    }  
  
    @Override  
    public void punsubscribe() {  
        super.punsubscribe();  
    }  
  
    @Override  
    public void punsubscribe(String... patterns) {  
        super.punsubscribe(patterns);  
    }  
    
  
    @Override  
    public void onPMessage(String pattern, String channel, String message) {  
  
    }  
    @Override  
    public void onPUnsubscribe(String pattern, int subscribedChannels) {  
  
    }  
    @Override  
    public void onPSubscribe(String pattern, int subscribedChannels) {  
  
    }  
}
