package cn.geekc.ssm.webapp.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.geekc.ssm.service.UserService;

@Controller
@RequestMapping(value = "/user")
public class UserController {

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/getuserbyid")
	@ResponseBody
	public ModelAndView getUserById(@RequestParam(value = "userId", required = true) Long userId,
			HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView("/test");
		modelAndView.addObject("user", userService.getUserById(userId));
		return modelAndView;
	}
}
