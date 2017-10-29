package com.meet.controller;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.meet.common.CommonUtils;
import com.meet.common.constants.OrderConstants;


/**
 * controller 基类
 * @author baizhixing
 *
 */
public abstract class BaseController {
	protected static Logger logger = LoggerFactory.getLogger(OrderConstants.LOG_NAME);
	
	protected HttpServletRequest request;
	protected HttpServletResponse response;
	protected HttpSession session;

	@ModelAttribute
	public void init(HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
		this.response = response;
		this.session = request.getSession();
	}

	/**
	 * 获取请求参数值
	 */
	public String getParam(String param) {
		return request.getParameter(param);
	}

	/**
	 * 获取参数转int
	 * 
	 * @param name
	 * @param defaultValue
	 * @return
	 */
	public Integer getParaToInt(String name, Integer defaultValue) {
		return toInt(request.getParameter(name), defaultValue);
	}

	public void setSessionAttr(String name, String value) {
		session.setAttribute(name, value);
	}

	public Object getSessionAttr(String name) {
		return session.getAttribute(name);
	}

	public void setAttr(String name, String value) {
		request.setAttribute(name, value);
	}

	private Integer toInt(String value, Integer defaultValue) {
		if (value == null || "".equals(value.trim()))
			return defaultValue;
		value = value.trim();
		if (value.startsWith("N") || value.startsWith("n"))
			return -Integer.parseInt(value.substring(1));
		return Integer.parseInt(value);
	}

	public Object getShiroSession(Object key) {
		Subject currentUser = SecurityUtils.getSubject();
		Object sessionValue = "";
		if (null != currentUser) {
			Session session = currentUser.getSession();
			if (null != session) {
				sessionValue = session.getAttribute("userId");
			}
		}
		return sessionValue;
	}
	
	/**
	 * 获取流参数值
	 * 
	 * @param request
	 * @return
	 * @throws IOException
	 */
	protected Map<String, String> getParaByInputStream() throws Exception {
		StringBuffer weiXinNotifyMsg = new StringBuffer("");
		try {
			BufferedReader rd = new BufferedReader(new InputStreamReader(request.getInputStream(), "UTF-8"));
			String responseStr = "";
			while ((responseStr = rd.readLine()) != null) {
				weiXinNotifyMsg.append(responseStr);
			}
			return CommonUtils.xmlToMap(weiXinNotifyMsg.toString());
		} catch (Exception e) {
			logger.error("request param get error:{}", e.getMessage());
			throw e;
		}

	}
	/**
	 * 获取调用方IP地址
	 * 
	 * @return
	 */
	protected String getIpAddr() {
		return request.getRemoteHost();
	}
	
	/**
	 * 返回信息到业务系统
	 * 
	 * @param rspXml
	 * @param response
	 */
	protected void responseWrite(String rspXml) {
		response.setContentType("text/xml;charset=utf-8");
		try {
			response.getWriter().write(rspXml);
			response.getWriter().flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
