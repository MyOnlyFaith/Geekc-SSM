package cn.geekc.ssm.memcached.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import net.spy.memcached.MemcachedClient;
import net.spy.memcached.internal.OperationFuture;

public class SourcesTest {
	
	private static Logger logger = LoggerFactory.getLogger(SourcesTest.class);
	
	private static final String KEY = "personList001";
	
	public static void main(String[] args) {
		//加载配置
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:applicationContext-cache.xml");
		MemcachedClient memcachedClient = (MemcachedClient) applicationContext.getBean("memcachedClient");
//		memcachedClient.set(KEY, 0, "nihao");
//		logger.info(""+memcachedClient.get(KEY));
//		OperationFuture<Boolean> of = memcachedClient.add(KEY, 0, "nibuhao");
//		System.err.println(of.getStatus().isSuccess());
//		memcachedClient.get(KEY);
//		memcachedClient.gets(KEY);
		System.err.println(memcachedClient.set(KEY, 100, "hehe").getStatus().isSuccess());
	}

	
	
}
