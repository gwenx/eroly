package com.eroly.util.cacheUpdate;

import java.util.HashMap;
import java.util.Map;

import com.eroly.util.StringUtil;

import redis.clients.jedis.JedisPubSub;

public class RedisMsgPubSubListener extends JedisPubSub {
	private Map<String , ICacheUpdate> updates = new HashMap<String , ICacheUpdate>();
	public boolean addUpdater(String key , ICacheUpdate update) {
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
        System.out.println("收到的消息----channel:" + channel + "收到的消息是 :" + message);
        ICacheUpdate updater = null;
        if(StringUtil.isNotEmpty(message)) {
        	updater = updates.get(message);
        }
        updater.update();
    }  
    /**
     * 取消订阅频道
     */
    @Override  
    public void onUnsubscribe(String channel, int subscribedChannels) {  
    	System.out.println("取消订阅频道:" + channel + "被订阅的数量:" + subscribedChannels);  
    }  
    /**
     * 订阅频道
     */
    @Override  
    public void onSubscribe(String channel, int subscribedChannels) {  
    	System.out.println("订阅频道:" + channel + "被订阅的数量:" + subscribedChannels);  
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
