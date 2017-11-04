package com.meet.controller;

import com.alibaba.fastjson.JSON;
import com.meet.common.constants.MeetConstants;
import com.meet.dto.req.SysUserInfoReq;
import com.meet.dto.rsp.ApiResponse;
import com.meet.orm.pojo.SysUser;
import com.meet.orm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class LoginController extends BaseController {

    @Autowired
    private UserService userService;

    /**
     * 用户登录
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Object login(SysUserInfoReq reqInfo) {
        logger.info("用户登录：{}", JSON.toJSONString(reqInfo));
        ApiResponse resp = new ApiResponse();
        try {
            SysUser user = userService.login(reqInfo);
            //添加用户到session
            setSessionAttr(MeetConstants.SESSION_USER_ID, String.valueOf(user.getId()));
            resp.setCode(MeetConstants.SYS_SUCCESS);
            resp.setMsg("登录成功");
        } catch (Exception e) {
            logger.error("登录失败:{}", e.getMessage());
            e.printStackTrace();
            resp.setCode(MeetConstants.SYS_FAILE);
            resp.setMsg(e.getMessage());
        }
        return resp;
    }


    /**
     * 用户登出
     */
    @RequestMapping("/logout")
    public Object logout() {
        logger.info("用户退出");
        ApiResponse resp = new ApiResponse();
        //添加用户到session
        removeSession(MeetConstants.SESSION_USER_ID);
        resp.setCode(MeetConstants.SYS_SUCCESS);
        resp.setMsg("退出成功");
        return resp;
    }
}
