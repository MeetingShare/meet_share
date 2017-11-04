package com.meet.dto.req;

import lombok.Data;

@Data
public class SysUserInfoReq extends SysRequest{
	//用户名
    private String username;
    //用户密码
    private String password;
    //状态
    private String active;
    //角色Id
    private String[] roleIds;
    //用户编号
    private String userId;
}
