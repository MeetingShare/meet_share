package com.meet.wechat.pojo.token;

import lombok.Data;

/**
 * 微信请求状态数据
 * 
 * @author baizhixing
 *
 */
@Data
public class BaseResult {
	private String errcode;
	private String errmsg;
}
