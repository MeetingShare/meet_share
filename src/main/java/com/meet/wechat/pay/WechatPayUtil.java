//package com.meet.wechat.pay;
//
//import java.util.Map;
//import java.util.UUID;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import com.alibaba.fastjson.JSON;
//import com.meet.common.CommonUtils;
//import com.meet.common.constants.OrderConstants;
//import com.meet.common.date.DateUtil;
//import com.meet.exception.BusinessException;
//import com.meet.orm.service.api.ApiReqWechatLogService;
//import com.meet.wechat.api.WechatApi;
//import com.meet.wechat.common.MapUtil;
//import com.meet.wechat.common.SignatureUtil;
//import com.meet.wechat.pojo.pay.PayJsRequest;
//import com.meet.wechat.pojo.pay.Unifiedorder;
//import com.meet.wechat.pojo.pay.UnifiedorderResult;
//
//@Component
//public class WechatPayUtil {
//	private Logger logger = LoggerFactory.getLogger(OrderConstants.LOG_NAME);
//
//	@Autowired
//	private ApiReqWechatLogService apiReqWechatLogService;
//
//	/**
//	 * 取openId后调用统一支付接口https://api.mch.weixin.qq.com/pay/unifiedorder
//	 *
//	 * @param spbill_create_ip
//	 * @param total_fee
//	 * @param openid
//	 * @param out_trade_no
//	 * @return
//	 */
//	public PayJsRequest bulidJSAPIRequestJson(String spbill_create_ip, String total_fee, String openid, String orderNo, String type) throws Exception {
//		String body = "不知火川味自助火锅超市";
//		// 随机数10位序列号,可以自行调整。
//		String nonce_str = DateUtil.format("yyyyMMdd") + CommonUtils.buildRandom(4);
//		// 总金额以分为单位，不带小数点
//		float sessionmoney = Float.parseFloat(total_fee);
//		String finalmoney = String.format("%.2f", sessionmoney);
//		finalmoney = finalmoney.replace(".", "");
//		// 这里notify_url是 支付完成后微信发给该链接信息，可以判断会员是否支付成功，改变订单状态等。
//		String trade_type = "JSAPI";
//		Unifiedorder unifiedorder = new Unifiedorder();
//		unifiedorder.setAppid(OrderConstants.WECHAT_APPID);
//		unifiedorder.setMch_id(OrderConstants.WECHAT_MACHID);
//		unifiedorder.setNonce_str(nonce_str);
//		unifiedorder.setBody(body);
//		unifiedorder.setOut_trade_no(orderNo);
//		unifiedorder.setTotal_fee(Integer.parseInt(finalmoney) + "");// 单位分
//		unifiedorder.setSpbill_create_ip(spbill_create_ip);// IP
//		unifiedorder.setNotify_url(String.format(OrderConstants.WECHAT_PAY_NOTIFY_URL,orderNo, type));
//		unifiedorder.setTrade_type(trade_type);// JSAPI，NATIVE，APP，WAP
//		unifiedorder.setOpenid(openid);
//		// 保存请求第三方数据
//		long startTime = System.currentTimeMillis();
//		long logId = 0;
//		try {
//			// 插入日志
//			logId = apiReqWechatLogService.addReqLog(JSON.toJSONString(unifiedorder), orderNo, type);
//		} catch (Exception e) {
//			logger.error("插入日期错误,不影响后续操作：{}", e.getMessage());
//		}
//		UnifiedorderResult unifiedorderResult = WechatApi.payUnifiedorder(unifiedorder, OrderConstants.WECHAT_PARTNERKEY);
//		long reqCostTime = System.currentTimeMillis() - startTime;
//		logger.info("调用微信总耗时：{}", reqCostTime);
//		String respText = JSON.toJSONString(unifiedorderResult);
//		logger.info("return json：{}" + respText);
//
//		if (!"SUCCESS".equals(unifiedorderResult.getReturn_code())) {
//			throw new BusinessException(unifiedorderResult.getReturn_msg());
//		}
//		try {
//			apiReqWechatLogService.updateReqLog(respText, reqCostTime, logId, unifiedorderResult.getReturn_code());
//		} catch (Exception e) {
//			logger.error("更新日期错误,不影响后续操作：{}", e.getMessage());
//		}
//		return generateMchPayJsRequest(unifiedorderResult.getPrepay_id(), OrderConstants.WECHAT_APPID, OrderConstants.WECHAT_PARTNERKEY);
//
//	}
//
//	/**
//	 * (MCH)生成支付JS请求对象
//	 *
//	 * @param prepay_id
//	 *            预支付订单号
//	 * @param appId
//	 * @param key
//	 *            商户支付密钥
//	 * @return
//	 */
//	private PayJsRequest generateMchPayJsRequest(String prepay_id, String appId, String key) {
//		String package_ = "prepay_id=" + prepay_id;
//		PayJsRequest payJsRequest = new PayJsRequest();
//		payJsRequest.setAppId(appId);
//		payJsRequest.setNonceStr(UUID.randomUUID().toString());
//		payJsRequest.setPackage_(package_);
//		payJsRequest.setSignType("MD5");
//		payJsRequest.setTimeStamp(System.currentTimeMillis() / 1000 + "");
//		Map<String, String> mapS = MapUtil.objectToMap(payJsRequest);
//		String paySign = SignatureUtil.generateSign(mapS, key);
//		payJsRequest.setPaySign(paySign);
//		return payJsRequest;
//	}
//}
