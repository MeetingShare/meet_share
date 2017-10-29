package com.meet.controller.sys;

import com.meet.common.constants.OrderConstants;
import com.meet.controller.BaseController;
import com.meet.orm.service.sys.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class IndexController extends BaseController {
	private static Logger log = LoggerFactory.getLogger("wechat_business");

	@Autowired
	UserService userService;

	/**
	 * 跳转登录页
	 */
	@RequestMapping("/login")
	public ModelAndView toLogin() {
		return new ModelAndView("login");
	}
	
	@RequestMapping("/")
	public ModelAndView toIndex(HttpServletRequest request) {
		log.info("访问首页");
		ModelAndView view = new ModelAndView();
		Integer userId = (int) getShiroSession(OrderConstants.SESSION_USER_Id);
		view.addObject("menus", userService.findUserMenu(userId));
		view.setViewName("index");
		return view;
	}
}
