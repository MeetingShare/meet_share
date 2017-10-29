package com.meet.common.constants;

import com.meet.common.resources.Prop;
import com.meet.common.resources.PropKit;

/**
 * 系统常亮 Created by bzhx on 2017年3月9日 上午11:28:47
 */
public class OrderConstants {

	private static Prop prop;
	static {
		prop = PropKit.use("config/properties/server.properties");
	}
	//是否默认 1-是默认
	public static final String IS_DEFAULT="1";
	
	public static final String NOT_DEFAULT="0";
	//逻辑删除标识
	public static final String IS_DELETE="1";
	//api接口返回状态信息
	public static final String API_RET_SUCCESS = "S";
	public static final String API_RET_FAIL = "F";
	//会员状态
	public static final String MEMBER_STATUS_JINYONG="1";
	//验证码发送时间
    public static final int SEND_CODE_TIME=15;
	//验证码cache name
	public static final String CACHE_SEND_CODE="sendCode";
	//门店cache name
	public static final String CACHE_STORE_ORDER_NO="storeOrderNo";
	//订单cache name
	public static final String CACHE_MEMBER_ORDER_NO="orderNo";
	/**
	 * 返回状态吗
	 */
	public static final String SYS_SUCCESS = "200";
	public static final String SYS_FAILE = "300";

	// 日志名称
	public static final String LOG_NAME = "meet";

	// 用户sessionkey
	public static final String SESSION_USER_NAME = "userName";
	public static final String SESSION_USER_Id = "userId";

	// 编码
	public static final String DEFAULT_ENCODING = "UTF-8";

	/**
	 * 图片上传相关
	 */
	// 图片访问路径路径
	public static final String IMAGE_URL = prop.get("image.url");
	// 图片上传绝对路径
	public static final String IMAGE_PATH = prop.get("image.path");
	
	/**
	 * 短信验证配置
	 */
	//请求地址
	public static final String SMS_ENDPOINT=prop.get("sms_endpoint");
	//apikey
	public static final String SMS_API_KEY=prop.get("sms_api_key");
	//sercertKey
	public static final String SMS_SECRET_KEY=prop.get("sms_secret_key");
	//模板ID
	public static final String SMS_TEMPLATE_ID=prop.get("sms_template_id");
	//短信签名
	public static final String SMS_SIGNNAME=prop.get("sms_signname");
	//host
	public static final String SMS_TOPIC=prop.get("sms_topic");
	
	/**
	 * 微信相关配置
	 */
	//appid
	public static final String WECHAT_APPID=prop.get("wechat_appid");
	public static final String WECHAT_SECRET=prop.get("wechat_secret");
	public static final String WECHAT_TOKEN=prop.get("wechat_token");
	public static final String WECHAT_URL=prop.get("wechat_url");
	
	//商户号
	public static final String WECHAT_MACHID=prop.get("wechat_mchid");
	//商户key
	public static final String WECHAT_PARTNERKEY=prop.get("wechat_partnerkey");
	//支付地址
	public static final String WECHAT_PAY_URL=prop.get("wechat_pay_url");
	//支付回调地址
	public static final String WECHAT_PAY_NOTIFY_URL=prop.get("wechat_pay_notify_url");
	//网页授权获取code地址
	public static final String WECHAT_OPEN_URL=prop.get("wechat_open_url");
	
	//openid存在在本地
	public static final String WECHAT_OPEN_ID="open_id";
	
	
	/**
	 * 关于配置支付类型 1-预定支付 2- 订单支付
	 */
	public static final String TYPE_REERVE="1";
	public static final String TYPE_ORDER="2";
	
	/**
	 * 预定支付状态 0-未支付 1-支付成功 2-支付失败 3-取消预定 4-已过期 5-已删除
	 */
	public static final String RESERVE_STATUS_NOT_PAY="0";
	public static final String RESERVE_STATUS_SUCCESS_PAY="1";
	public static final String RESERVE_STATUS_FAIL_PAY="2";
	public static final String RESERVE_STATUS_CANCEL="3";
	public static final String RESERVE_STATUS_OVER="4";
	public static final String RESERVE_STATUS_DELETE="5";
	
	/**
	 * 订单状态 0-未支付 1-支付成功 2-支付失败 3-失效关闭
	 */
	public static final String ORDER_STATUS_NOT_PAY="0";
	public static final String ORDER_STATUS_SUCCESS_PAY="1";
	public static final String ORDER_STATUS_FAIL_PAY="2";
	public static final String ORDER_STATUS_CANCEL="3";
	/**
	 * 订单发货状态
	 */
	public static final String ORDER_SET_STATE_FAHUO="4";
	public static final String ORDER_SET_STATE_SHOUHUO="5";
	public static final String ORDER_SET_STATE_COMPLATE="6";
	
	/**
	 * 支付方式
	 */
	public static final String PAY_TYPE_ONLINE="0";
	public static final String PAY_TYPE_DOWN="1";
	
}
