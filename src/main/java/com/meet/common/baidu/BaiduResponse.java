package com.meet.common.baidu;

import lombok.Data;

/**
 * @Title: BaiduResponse.java
 * @Package com.order.common.baidu
 * @Description: TODO(百度地图返回)
 * @author baizhixing
 * @date 2017年5月20日 上午9:24:21
 * @version V1.0
 */
@Data
public class BaiduResponse {
	// 状态
	private Integer status;

	private RetResult result;

}
