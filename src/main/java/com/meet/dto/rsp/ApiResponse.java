package com.meet.dto.rsp;

import lombok.Data;

/**   
 * @Title: ApiResponse.java 
 * @Package com.car.dto.resp 
 * @Description: TODO(API接口返回对象) 
 * @author baizhixing   
 * @date 2017年5月8日 下午3:51:15 
 * @version V1.0   
 */
@Data
public class ApiResponse {
	//返回状态吗
	private String code;
	//返回错误信息
	private String msg;
	//返回数据
	private Object data;
}
