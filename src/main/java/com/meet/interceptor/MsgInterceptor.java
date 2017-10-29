package com.meet.interceptor;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.meet.common.constants.OrderConstants;
import com.meet.exception.BusinessException;
import com.meet.wechat.common.SignatureUtil;


public class MsgInterceptor implements HandlerInterceptor {

	private static  Logger log = LoggerFactory.getLogger(OrderConstants.LOG_NAME);
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		// TODO Auto-generated method stub
		/**
		 * 是否为开发者中心保存服务器配置的请求
		 */
		if (StringUtils.isNoneBlank(request.getParameter("echostr"))) {
			render(response,request.getParameter("echostr"));
			return false;
		}
		// 签名检测
		if (checkSignature(request,response)) {
			return true;
		} else {
			render(response,"check signature failure");
		}
		
		return false;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
		// TODO Auto-generated method stub
		
	}

	/**
	 * 检测签名
	 */
	private boolean checkSignature(HttpServletRequest request,HttpServletResponse response) throws Exception{
		String signature = request.getParameter("signature");
		String timestamp = request.getParameter("timestamp");
		String nonce = request.getParameter("nonce");
		if (StringUtils.isBlank(signature) || StringUtils.isBlank(timestamp)
				|| StringUtils.isBlank(nonce)) {
			render(response,"check signature failure");
			return false;
		}

		// 验证请求签名
		if (!signature.equals(SignatureUtil.generateEventMessageSignature(
				OrderConstants.WECHAT_TOKEN, timestamp, nonce))) {
			log.error("check signature failure: " + " signature = "
					+ request.getParameter("signature") + " timestamp = "
					+ request.getParameter("timestamp") + " nonce = "
					+ request.getParameter("nonce"));

			return false;
		} else {
			return true;
		}
	}
	public void render(HttpServletResponse response,String text) throws Exception{
		PrintWriter writer = null;
		try {
			response.setHeader("Pragma", "no-cache");	// HTTP/1.0 caches might not implement Cache-Control and might only implement Pragma: no-cache
	        response.setHeader("Cache-Control", "no-cache");
	        response.setDateHeader("Expires", 0);
	        	response.setContentType("text/plain; charset=UTF-8");
				response.setCharacterEncoding("UTF-8");
	        writer = response.getWriter();
	        writer.write(text);
	        writer.flush();
		} catch (IOException e) {
			throw new BusinessException(e);
		}
		finally {
			if (writer != null)
				writer.close();
		}
	}
}
