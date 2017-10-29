package com.meet.wechat.pojo.token;

import lombok.Data;

@Data
public class SnsToken extends BaseResult {
	
	private String access_token;

	private Integer expires_in;

	private String refresh_token;

	private String openid;

	private String scope;
}
