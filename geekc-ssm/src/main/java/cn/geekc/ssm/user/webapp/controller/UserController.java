package cn.geekc.ssm.user.webapp.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.geekc.ssm.base.webapp.controller.BaseController;
import cn.geekc.ssm.user.service.UserService;

@Controller
@RequestMapping(value = "/user")
public class UserController extends BaseController {

	private static Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/getuserbyid")
	@ResponseBody
	public ModelAndView getUserById(@RequestParam(value = "userId", required = true) Long userId,
			HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView("/test");
		modelAndView.addObject("user", userService.getUserById(userId));
		logger.info(getClientIpAddr(request) + "--");
		logger.info(getRealPath(request) + "--");
		logger.info(request.getSession().getId() + "==");
		return modelAndView;
	}
}
