package com.meet.common;

import com.meet.common.date.DateUtil;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.StringReader;
import java.text.DecimalFormat;
import java.util.*;

/**
 * @Title: CommonUtils.java
 * @Package com.order.common
 * @Description: TODO(通用工具类)
 * @author baizhixing
 * @date 2017年5月22日 上午11:33:52
 * @version V1.0
 */
public class CommonUtils {

	/**
	 * 作用：Map转xml
	 */
	public static String arrayToXml(Map<String, String> paras) {
		// TODO
		SortedMap<String, String> paraMap = new TreeMap<String, String>();
		paraMap.putAll(paras);
		String xml = "<xml>";
		Set es = paraMap.entrySet();
		Iterator it = es.iterator();
		while (it.hasNext()) {
			Map.Entry entry = (Map.Entry) it.next();
			String k = (String) entry.getKey();
			String v = (String) entry.getValue();
			xml += "<" + k + ">" + v + "</" + k + ">";
		}
		xml += "</xml>";

		return xml;
	}

	/**
	 * 作用：将xml转为Map
	 */
	public static Map<String, String> xmlToMap(String xmlStr) throws Exception {
		// TODO
		Map<String, String> rtnMap = new HashMap<String, String>();
		SAXReader builder = new SAXReader();
		Document doc = null;
		try {
			doc = builder.read(new StringReader(xmlStr));
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			throw new IllegalArgumentException("parse xml to map error！");
		}
		// 得到根节点
		Element root = doc.getRootElement();
		// 获取所有子元素
		for (Iterator i = root.elementIterator(); i.hasNext(); ) {
			Element el = (Element) i.next();
			rtnMap.put(el.getName(), el.getText());

		}
		return rtnMap;
	}

	/**
	 * 计算两点之间距离
	 *
	 * @param
	 * @param
	 * @return 米
	 */
	public static String getDistance(Double longitude, Double latitude, Double addressX, Double addressY) {
		double lon1 = (Math.PI / 180) * longitude;
		double lon2 = (Math.PI / 180) * addressX;// 经度

		double lat1 = (Math.PI / 180) * latitude;
		double lat2 = (Math.PI / 180) * addressY;// 维度

		// 地球半径
		double R = 6371;

		// 两点间距离 km，如果想要米的话，结果*1000就可以了
		double d = Math.acos(Math.sin(lat1) * Math.sin(lat2) + Math.cos(lat1) * Math.cos(lat2) * Math.cos(lon2 - lon1)) * R;
		/* d=d*1000; */
		DecimalFormat df = new DecimalFormat("0.00");
		return df.format(d);
	}

	/**
	 * 取出一个指定长度大小的随机正整数.
	 * @param length int 设定所取出随机数的长度。length小于11
	 * @return int 返回生成的随机数。
	 */
	public static int buildRandom(int length) {
		int num = 1;
		double random = Math.random();
		if (random < 0.1) {
			random = random + 0.1;
		}
		for (int i = 0; i < length; i++) {
			num = num * 10;
		}
		return (int) ((random * num));
	}
	public static String getMeetNo(){
		String dateTime= DateUtil.format("yyyyMMddHHmmssSS");
		int random=new Random().nextInt(999999999);
		return dateTime+random;
	}
}
