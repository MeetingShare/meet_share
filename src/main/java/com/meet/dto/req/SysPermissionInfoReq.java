package com.meet.dto.req;

import lombok.Data;

/**
 * 权限请求对象 Created by bzhx on 2017年3月17日 下午3:42:09
 */
@Data
public class SysPermissionInfoReq extends SysRequest {
	// 资源名称
	private String name;
	// 资源地址
	private String controllerUrl;
	// 权重
	private int weight;
	// 父类Id
	private int fId;
	// 主键ID
	private int id;

}
