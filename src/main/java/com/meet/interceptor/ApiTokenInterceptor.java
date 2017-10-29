package com.meet.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.meet.common.constants.OrderConstants;
import com.meet.wechat.support.TokenManager;

/**
* @Title: ApiOpenIdInterceptor.java 
* @Package com.order.interceptor 
* @Description: TODO(拦截器用来获取acctoken) 
* @author baizhixing   
* @date 2017年5月22日 下午1:58:59 
* @version V1.0
 */
public class ApiTokenInterceptor implements HandlerInterceptor{

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		// TODO Auto-generated method stub
		TokenManager.init();
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
		// TODO Auto-generated method stub
		
	}

}
