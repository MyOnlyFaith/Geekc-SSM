package cn.geekc.ssm.user.service;

import org.springframework.stereotype.Component;

import cn.geekc.ssm.user.model.User;

@Component
public interface UserService {
	
	User getUserById(Long userId);
	
}
