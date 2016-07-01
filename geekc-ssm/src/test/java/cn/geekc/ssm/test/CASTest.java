package cn.geekc.ssm.test;

import java.io.IOException;
import java.net.InetSocketAddress;

import org.junit.Test;

import net.spy.memcached.CASResponse;
import net.spy.memcached.CASValue;
import net.spy.memcached.MemcachedClient;

public class CASTest {
	
	@Test
	public void casTest() throws Exception{
		MemcachedClient cache = new MemcachedClient(new InetSocketAddress("127.0.0.1", 11211));  
	    cache.set("number", 1800, "number");  
	    for (int i = 0; i < 2; i++) {  
	        new CASThread("thread--" + i, "number" + i).start();  
	    } 
	}
}
class CASThread extends Thread {  
    private MemcachedClient cache;  
    private String value;  
  
    public CASThread(String name, String value) throws Exception {  
        super(name);  
        cache = new MemcachedClient(new InetSocketAddress("127.0.0.1", 11211));  
        this.value = value;  
    }  
  
    @Override  
    public void run() {  
        int i = 0;  
        while (i < 5) {  
            i++;  
            CASValue<Object> uniqueValue = cache.gets("number");  
            CASResponse response = cache.cas("number", uniqueValue.getCas(), value);  
            if (response.toString().equals("OK")) {  
                System.out.println(Thread.currentThread().getName() + " update success:"+response);  
//                System.out.println(Thread.currentThread().getName()+":"+cache.get("number"));
            }else{  
                System.out.println(Thread.currentThread().getName() + " update fail:"+response);  
//                System.out.println(Thread.currentThread().getName()+":"+cache.get("number"));
            }  
        }  
        System.out.println(Thread.currentThread().getName()+":"+cache.get("number"));
    }  
}
