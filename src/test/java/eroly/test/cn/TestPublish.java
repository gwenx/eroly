package eroly.test.cn;

import redis.clients.jedis.Jedis;

public class TestPublish {
	public static void main(String[] args) {
		try {
			Jedis jedis = new Jedis("localhost");  
			jedis.publish("redisChatTest", "我是天才");  
			Thread.sleep(5000);
			jedis.publish("haha", "我牛逼");  
			Thread.sleep(5000);  
			jedis.publish("redisChatTest", "哈哈");  
		} catch (InterruptedException e) {
			e.printStackTrace();
		}  
		
	}
}
