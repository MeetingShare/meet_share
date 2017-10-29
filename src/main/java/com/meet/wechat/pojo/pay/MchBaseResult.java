package com.meet.wechat.pojo.pay;

import lombok.Data;

@Data
public class MchBaseResult {

	protected String return_code;

	protected String return_msg;

	protected String appid;

	protected String mch_id;

	protected String nonce_str;

	protected String sign;

	protected String result_code;

	protected String err_code;

	protected String err_code_des;
}
