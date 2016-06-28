package cn.geekc.ssm.cache.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.geekc.ssm.cache.service.MemcachedService;
import net.spy.memcached.MemcachedClient;

@Service
public class MemcachedServiceImpl implements MemcachedService {
	
	@Resource(name = "memcachedClient")
	private MemcachedClient memcachedClient;

	public Object get(String key) {
		return memcachedClient.get(key);
	}

	

}
