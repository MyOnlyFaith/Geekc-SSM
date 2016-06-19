package cn.geekc.ssm.service;

import org.springframework.stereotype.Component;

import cn.geekc.ssm.model.User;

@Component
public interface UserService {
	
	User getUserById(Long userId);
	
}
