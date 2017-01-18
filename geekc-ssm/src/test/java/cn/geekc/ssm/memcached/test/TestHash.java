package cn.geekc.ssm.memcached.test;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import net.spy.memcached.CASResponse;
import net.spy.memcached.CASValue;
import net.spy.memcached.DefaultHashAlgorithm;
import net.spy.memcached.MemcachedClient;
import net.spy.memcached.internal.OperationFuture;

/**
 * list<=400000条
 * map<=200000条
 * @author geekc
 * 
 * @date 2016年7月6日
 */
public class TestHash {
	
	@SuppressWarnings({ "unchecked", "resource" })
	public static void main(String[] args) throws InterruptedException {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:applicationContext-cache.xml");
		MemcachedClient memcachedClient = (MemcachedClient) applicationContext.getBean("memcachedClient");
		System.out.println(stringToHash("personList1"));
		List<Person> list = new ArrayList<Person>();
		Map<Integer,Person> map = new HashMap<Integer,Person>();
		long begin = System.currentTimeMillis();
		for(int i=0;i<200000;i++){
			Person p =new Person("tom"+i, 20, "man", "ss");
			list.add(p);
//			map.put(i, p);
		}
		System.out.println("for time:"+(System.currentTimeMillis()-begin)/1000+"."+(System.currentTimeMillis()-begin)%1000+"秒");
		System.out.println("list.size:"+list.size());
		String key = stringToHash("testList")+"";
		System.out.println("key:"+key);
		long setBegin = System.currentTimeMillis();
		CASValue<Object> cas = memcachedClient.gets(key);
		if(null == cas){
			OperationFuture<Boolean> of = memcachedClient.add(key, 0, list);
			System.out.println("set time:"+(System.currentTimeMillis()-setBegin)/1000+"."+(System.currentTimeMillis()-setBegin)%1000+"秒");
			System.out.println("set result:"+of.getStatus().getMessage());
		} else {
			long casId = cas.getCas();
			System.out.println("casId:"+casId);
			CASResponse response = memcachedClient.cas(key, casId, 0, list);
			System.out.println("set time:"+(System.currentTimeMillis()-setBegin)/1000+"."+(System.currentTimeMillis()-setBegin)%1000+"秒");
			System.out.println("set result:"+response);
		}
//		Thread.sleep(2000);
		long getBegin = System.currentTimeMillis();
//		Map<Integer,Person> obj = (Map<Integer,Person>) memcachedClient.get(key);
		List<Person> obj = (List<Person>) memcachedClient.get(key);
		System.out.println("get time:"+(System.currentTimeMillis()-getBegin)/1000+"."+(System.currentTimeMillis()-getBegin)%1000+"秒");
		System.out.println("obj size:"+obj.get(100).getName());
	}
	
	@Test
	public void testString(){
		System.out.println(stringToHash(""));
		System.out.println(stringToHash(" "));
		System.out.println(stringToHash(" "));
	}
	
	private static long stringToHash(String key) {
        long rv = 0;
        byte[] bKey = DefaultHashAlgorithm.computeMd5(key);
        rv = ((long) (bKey[3] & 0xFF) << 24)
          | ((long) (bKey[2] & 0xFF) << 16)
          | ((long) (bKey[1] & 0xFF) << 8)
          | (bKey[0] & 0xFF);
        return rv & 0xffffffffL; /* Truncate to 32-bits */
    }
}
class Person implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6882283008494960144L;
	private String name;
	private Integer age;
	private String sex;
	private String addr;
	
	public Person(String name, Integer age, String sex, String addr) {
		super();
		this.name = name;
		this.age = age;
		this.sex = sex;
		this.addr = addr;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
}