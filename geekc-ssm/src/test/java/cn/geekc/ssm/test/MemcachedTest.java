package cn.geekc.ssm.test;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import net.spy.memcached.MemcachedClient;


public class MemcachedTest {
	
	private static Logger logger = LoggerFactory.getLogger(MemcachedTest.class);
	
	@Test
	public void test() throws InterruptedException{
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:applicationContext-cache.xml");
		MemcachedClient client = (MemcachedClient) applicationContext.getBean("memcachedClient");
		client.set("user", 200, "haha");
		Thread.sleep(5000);
		logger.info("client get data :"+client.get("user"));
	}
	
}
