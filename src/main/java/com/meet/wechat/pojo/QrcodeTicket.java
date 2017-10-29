package com.meet.wechat.pojo;

import com.meet.wechat.pojo.token.BaseResult;

import lombok.Data;

/**
 * 二维码 ticket
 * @author baizhixing
 *
 */
@Data
public class QrcodeTicket extends BaseResult{
	
	private String ticket;
	
	private Integer expire_seconds;
}