package com.meet.common.baidu;

import java.net.URLEncoder;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.meet.common.constants.MeetConstants;
import com.meet.exception.BusinessException;

public class BaiduClient {
	private static Logger logger = LoggerFactory.getLogger(MeetConstants.LOG_NAME);
	private static String REQ_URL = "http://api.map.baidu.com/geocoder/v2/?address=%s&output=json&ak=42d35fc1c384079a55f6cd1eeb0b7715&callback=showLocation";

	public static BaiduResponse reqBaiduGetLanAndLgn(String address) throws BusinessException {
		String returnJson=null;
		try {
			HttpClient client = new DefaultHttpClient();
			String reqUrl = String.format(REQ_URL, URLEncoder.encode(address, MeetConstants.DEFAULT_ENCODING));
			logger.info("获取店铺地址坐标:{}", reqUrl);
			HttpGet get = new HttpGet(reqUrl);
			HttpResponse response = client.execute(get);
			int status = response.getStatusLine().getStatusCode();
			if (status >= 200 && status < 300) {
				HttpEntity entity = response.getEntity();
				returnJson = EntityUtils.toString(entity, "UTF-8");
				returnJson=returnJson.substring(returnJson.indexOf("{"), returnJson.lastIndexOf("}")+1);
				logger.info("调用百度api返回数据:{}", returnJson);
				
			} 
		} catch (Exception e) {
			// TODO: handle exception
			throw new BusinessException("调用百度地图API异常:" + e.getMessage());
		}
		if(StringUtils.isEmpty(returnJson)){
			throw new BusinessException("调用百度地图API异常：数据返回错误！");
		}
		BaiduResponse response=JSON.parseObject(returnJson,BaiduResponse.class);
		return response;
	}
public static void main(String[] args) {
	try {
		reqBaiduGetLanAndLgn("北京市海淀区上地十街10号");
	} catch (BusinessException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
}
