package cn.geekc.ssm.test;

import java.net.InetSocketAddress;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import net.spy.memcached.MemcachedClient;
import net.spy.memcached.internal.OperationFuture;

public class NoCASTest {
	private static Logger logger = LoggerFactory.getLogger(NoCASTest.class);
	static final int EXP = 60 * 60 * 24 * 30;
	ApplicationContext applicationContext = null;
	MemcachedClient cache = null;

	@Before
	public void before() {
		// logger.info("======" + "加载spring配置文件" + "=======");
		// applicationContext = new
		// ClassPathXmlApplicationContext("classpath:applicationContext-cache.xml");
		// cache = (MemcachedClient)
		// applicationContext.getBean("memcachedClient");
		// logger.info("======" + "加载spring配置文件完成" + "=======");
	}

	@Test
	public void noCasTest() throws Exception {
		cache = new MemcachedClient(new InetSocketAddress("127.0.0.1", 11211));
		cache.set("key", EXP, "key");
		// logger.info("del"+cache.delete("key").getStatus());
		logger.info("get:" + cache.get("key"));
		new MThread("thread--1", "X").start();
		new MThread("thread--2", "Y").start();
	}
}

class MThread extends Thread {
	private MemcachedClient cache;
	private String value;

	public MThread(String name, String value) throws Exception {
		super(name);
		cache = new MemcachedClient(new InetSocketAddress("127.0.0.1", 11211));
		this.value = value;
	}

	@Override
	public void run() {
		OperationFuture<Boolean> future = cache.set("key", NoCASTest.EXP, value);
		System.out.println(Thread.currentThread().getName() + ":" + future.getStatus().getStatusCode());
//		try {
//			sleep(10);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		System.out.println(Thread.currentThread().getName() + "--" + cache.get("key"));
	}
}
