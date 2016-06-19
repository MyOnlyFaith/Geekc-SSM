package cn.geekc.ssm.webapp.controller;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.geekc.ssm.model.User;
import cn.geekc.ssm.service.UserService;

@Controller
@RequestMapping(value="/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/getuserbyid")
	@ResponseBody
	public User getUserById(@RequestParam(value="userId",required = true)Long userId){
		return userService.getUserById(userId);
	}
}
