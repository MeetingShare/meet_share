package com.meet.wechat.pojo.pay;

import lombok.Data;

import com.alibaba.fastjson.annotation.JSONField;

@Data
public class PayJsRequest {

	private String appId;

	private String timeStamp;

	private String nonceStr;

	@JSONField(name="package")
	private String package_;

	private String signType;

	private String paySign;
	
	private Integer id;
}