package com.meet.dto.req;

import lombok.Data;

/**
 * Created by bzhx on 2017-11-05 22:36.
 */
@Data
public class AccountInfoReq extends SysRequest {
    // 用户ID
    private Integer id;
    // 手机号
    private String account;
    //密码
    private String password;
    // 验证码
    private String code;
    // 头像
    private String headImg;
    // 昵称
    private String nickName;
    //openID
    private String openId;
    //星座
    private String hrdStar;
   //邮箱
    private String mail;

    //状态
    private String status;
    /**
     * 用户修改密码参数
     */
    // 原始密码
    private String originalPass;
    // 新密码
    private String newPass;
    // 确认密码
    private String confirmPass;
}