package com.meet.wechat.support;

import com.meet.common.constants.MeetConstants;
import com.meet.wechat.api.WechatApi;
import com.meet.wechat.pojo.token.Token;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * TokenManager token 自动刷新
 *
 * @author baizhixing
 *
 */
public class TokenManager {
    private static Logger log=Logger.getLogger(TokenManager.class.getName());
    private static Map<String, String> tokenMap = new LinkedHashMap<String, String>();
	private static Long expiredTime;// 正确获取到 access_token 时有值，存放过期时间
	/**
	 * 初始化token 刷新，每118分钟刷新一次。
	 */
	public static void init() {
		if (StringUtils.isEmpty(getDefaultToken())||!isAvailable()) {
			log.info("验证access_token 为空,请求地址");
			Token token = WechatApi.token();
			if(token.getExpires_in()!=null)
				expiredTime = System.currentTimeMillis() + ((token.getExpires_in() -5) * 1000);
			tokenMap.put(MeetConstants.WECHAT_APPID, token.getAccess_token());
		} else {
			log.info("access_token 存在值");
		}
	}
	private static boolean isAvailable() {
		if (expiredTime == null)
			return false;
		if (expiredTime < System.currentTimeMillis())
			return false;
		return true;
	}

	/**
	 * 获取 access_token
	 *
	 * @param appid
	 * @return
	 */
	public static String getToken(String appid) {
		return tokenMap.get(appid);
	}

	/**
	 * 获取第一个appid 的 access_token 适用于单一微信号
	 *
	 * @param appid
	 * @return
	 */
	public static String getDefaultToken() {
		Object[] objs = tokenMap.values().toArray();
		if (objs.length > 0)
			return objs[0].toString();

		return objs.length > 0 ? objs[0].toString() : null;
	}

}
