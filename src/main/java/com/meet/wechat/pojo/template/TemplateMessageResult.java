package com.meet.wechat.pojo.template;

import lombok.Data;

import com.meet.wechat.pojo.token.BaseResult;
@Data
public class TemplateMessageResult extends BaseResult{

	private Long msgid;

	public Long getMsgid() {
		return msgid;
	}

	public void setMsgid(Long msgid) {
		this.msgid = msgid;
	}


}
