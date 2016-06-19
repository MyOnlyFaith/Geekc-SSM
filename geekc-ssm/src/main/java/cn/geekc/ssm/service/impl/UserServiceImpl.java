package cn.geekc.ssm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.geekc.ssm.dao.UserMapper;
import cn.geekc.ssm.model.User;
import cn.geekc.ssm.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userMapper;

	public User getUserById(Long userId) {
		return userMapper.selectByPrimaryKey(userId);
	}
	
}
