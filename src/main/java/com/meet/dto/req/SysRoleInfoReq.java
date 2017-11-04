package com.meet.dto.req;

import lombok.Data;

@Data
public class SysRoleInfoReq extends SysRequest {
	// 角色名称
	private String name;
	// 角色ID
	private String roleId;
	// 權限Id
	private String moduleIds;

}
