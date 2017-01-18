package cn.geekc.ssm.memcached.test;

import java.io.Serializable;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import net.spy.memcached.MemcachedClient;
import net.spy.memcached.NodeLocator;
import net.spy.memcached.internal.GetFuture;

public class MemcachedTest {

	private static Logger logger = LoggerFactory.getLogger(MemcachedTest.class);
	private static final int EXP = 0;
	ApplicationContext applicationContext = null;
	MemcachedClient memcachedClient = null;

	@Before
	public void before() {
		logger.info("======" + "加载spring配置文件" + "=======");
		applicationContext = new ClassPathXmlApplicationContext("classpath:applicationContext-cache.xml");
		memcachedClient = (MemcachedClient) applicationContext.getBean("memcachedClient");
		logger.info("======" + "加载spring配置文件完成" + "=======");
	}

	@Test
	public void test() throws InterruptedException {
		// cn.geekc.ssm.test.User u = null;
		// 1、asyncGet:用异步的方式，查询缓存中是否存在key:someKey
		// logger.info("asyncGet(1):"+memcachedClient.asyncGet("someKey").getStatus().isSuccess());
		// logger.info("asyncGet(1,2):"+memcachedClient.asyncGet("someKey",new
		// IntegerTranscoder()).getStatus().isSuccess());
		// 2、get方法取出key为someKey的数据，但是也是先执行asyncGet去判断someKey是否存在于缓存中
		// logger.info("get(1):"+memcachedClient.get("someKey"));
		// 3、如果不是String，Long，Integer，Boolean，Date，Byte，Float，Double，byte[]这些类型，则需要序列化，如果add的时候，key已经存在，则add失败，返回false
		// logger.info("add(1,2,3):"+memcachedClient.add("someKey", EXP, new
		// User()).getStatus().isSuccess());
		// logger.info("add(1,2,3,4):"+memcachedClient.add("someKey", EXP, new
		// User(), new SerializingTranscoder()).getStatus().isSuccess());
		// 4、CAS协议

		// logger.info("set(1,2,3):"+memcachedClient.set("", , o));

	}

	@Test
	public void testCAS() {
		// logger.info("get:"+memcachedClient.get("someKey"));
		// logger.info("gets:"+memcachedClient.gets("someKey").getCas());
		// CASResponse response = memcachedClient.cas("someKey",
		// memcachedClient.gets("someKey").getCas(), "hello");
		// logger.info("cas:"+response);
		// logger.info("get:"+memcachedClient.get("someKey"));
		// logger.info("gets:"+memcachedClient.gets("someKey").getCas());
		// logger.info("del"+memcachedClient.delete("someKey").getStatus());
		// List<User> list = new ArrayList<User>();
		// list.add(new User());
		// logger.info("hash-3:"+list);
		// System.out.println(memcachedClient.getNodeLocator().getPrimary("someKey"));
		// MemcachedNode mn =
		// memcachedClient.getNodeLocator().getPrimary("someKey");
		// DefaultKetamaNodeLocatorConfiguration dknlc = new
		// DefaultKetamaNodeLocatorConfiguration();
		// System.out.println(dknlc.getKeyForNode(mn, 100));
		// memcachedClient.g
		// String testKet = "";
		// for(int i=1;i<251;i++){
		// testKet+=1;
		// }
		// System.out.println("---------");
		// System.out.println(memcachedClient.set(testKet, 0,
		// "testKeyLength").getStatus().getStatusCode());
		// System.out.println(memcachedClient.get(testKet));
		//
		// logger.info("getNodeRepetitions:"+dknlc.getNodeRepetitions());
	}

	@Test
	public void testMemcachedMethod(){
		NodeLocator nodeLocator = memcachedClient.getNodeLocator();
		System.out.println(nodeLocator.getAll());
//		System.out.println(memcachedClient.getTranscoder().encode(new User()));
		System.out.println(memcachedClient.add("a", 0, "addtest1").getStatus().isSuccess());;
//		System.out.println(memcachedClient.get("add"));
//		GetFuture<Object> getFuture = memcachedClient.asyncGet("add");
//		System.out.println(getFuture.getStatus().isSuccess());
//		System.out.println(getFuture.get(duration, units));
	}

}

class User implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6013895656750590608L;
	private String name;
	private String email;
	private int age;
	private String sex;
	private float height;
	private float weight;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public float getHeight() {
		return height;
	}

	public void setHeight(float height) {
		this.height = height;
	}

	public float getWeight() {
		return weight;
	}

	public void setWeight(float weight) {
		this.weight = weight;
	}
}
