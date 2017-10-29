package com.meet.dto.req.sys;

import lombok.Data;

import com.meet.dto.req.SysRequest;

@Data
public class SysRoleInfoReq extends SysRequest {
	// 角色名称
	private String name;
	// 角色ID
	private String roleId;
	// 權限Id
	private String moduleIds;

}
