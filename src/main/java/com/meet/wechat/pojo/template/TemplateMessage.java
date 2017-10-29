package com.meet.wechat.pojo.template;


import lombok.Data;

import java.util.LinkedHashMap;

@Data
public class TemplateMessage {

	private String touser;

	private String template_id;

	private String url;

	private String topcolor;

	private LinkedHashMap<String,TemplateMessageItem> data;



}
