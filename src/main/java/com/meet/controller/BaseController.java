package com.meet.controller;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.meet.common.constants.MeetConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.meet.common.CommonUtils;


/**
 * controller 基类
 * @author baizhixing
 *
 */
public abstract class BaseController {
	protected static Logger logger = LoggerFactory.getLogger(MeetConstants.LOG_NAME);

	protected HttpServletRequest request;
	protected HttpServletResponse response;
	protected HttpSession session;

	@ModelAttribute
	public void init(HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
		this.response = response;
		this.session = request.getSession();
	}



	public void setSessionAttr(String name, String value) {
		session.setAttribute(name, value);
	}
	public void removeSession(String name) {
		session.removeAttribute(name);
	}

}
