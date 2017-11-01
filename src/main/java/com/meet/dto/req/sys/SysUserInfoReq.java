package com.meet.dto.req.sys;

import lombok.Data;

import com.meet.dto.req.SysRequest;

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
