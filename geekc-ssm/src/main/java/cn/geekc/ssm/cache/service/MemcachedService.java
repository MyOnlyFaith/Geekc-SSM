package cn.geekc.ssm.cache.service;

import org.springframework.stereotype.Component;

@Component
public interface MemcachedService {
	
	public Object get(String key);
	
}
