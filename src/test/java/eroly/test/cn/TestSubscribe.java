package eroly.test.cn;

import com.eroly.util.cacheUpdate.RedisMsgPubSubListener;

import redis.clients.jedis.Jedis;

public class TestSubscribe {
	public static void main(String[] args) {
		try {
			Jedis jedis = new Jedis("localhost");
			RedisMsgPubSubListener listener = new RedisMsgPubSubListener();
			jedis.subscribe(listener, "redisChatTest","haha","dadad");
//			jedis.subscribe(listener, "redisChatTest");
		} catch (Exception e) {
			e.printStackTrace();
		}  
	}
}
