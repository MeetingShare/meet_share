package com.meet.wechat.pojo.token;

import lombok.Data;

@Data
public class Token extends BaseResult {

	private String access_token;
	private Integer expires_in;


}