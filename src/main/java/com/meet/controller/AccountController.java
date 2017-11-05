package com.meet.controller;

import com.alibaba.fastjson.JSON;
import com.meet.common.constants.MeetConstants;
import com.meet.dto.req.AccountInfoReq;
import com.meet.dto.rsp.ApiResponse;
import com.meet.orm.pojo.AccountInfo;
import com.meet.orm.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by bzhx on 2017-11-05 22:29.
 */
@RestController
@RequestMapping("/api/member")
public class AccountController extends BaseController {

    @Autowired
    private AccountService accountService;

    /**
     * 会员列表
     */
    @RequestMapping("/page_list")
    public Object list(AccountInfoReq reqInfo) {
        logger.info("访问用户列表");
        ApiResponse resp = new ApiResponse();
        resp.setCode(MeetConstants.SYS_SUCCESS);
        resp.setMsg("数据获取成功");
        resp.setData(accountService.findAccountListPage(reqInfo));
        return resp;
    }
    /**
     * 发送验证码
     */
    @RequestMapping("/sendCode")
    @ResponseBody
    public Object sendCode(AccountInfoReq reqInfo) {
        logger.info("发送验证请求数据:{}", JSON.toJSONString(reqInfo));
        ApiResponse resp = new ApiResponse();
        try {
            accountService.sendCode(reqInfo);
            resp.setCode(MeetConstants.API_RET_SUCCESS);
            resp.setMsg("验证码发送成功");
        } catch (Exception e) {
            logger.error("验证码发送异常：{}", e.getMessage());
            e.printStackTrace();
            resp.setCode(MeetConstants.API_RET_FAIL);
            resp.setMsg("验证码发送失败");
        }
        return resp;
    }

    /**
     * 注册
     */
    @RequestMapping("/register")
    @ResponseBody
    public Object register(AccountInfoReq reqInfo) {
        logger.info("注册请求数据:{}", JSON.toJSONString(reqInfo));
        ApiResponse resp = new ApiResponse();
        try {
            accountService.register(reqInfo);
            resp.setCode(MeetConstants.API_RET_SUCCESS);
            resp.setMsg("注册失败");
        } catch (Exception e) {
            logger.error("注册异常：{}", e.getMessage());
            e.printStackTrace();
            resp.setCode(MeetConstants.API_RET_FAIL);
            resp.setMsg(e.getMessage());
        }
        return resp;
    }

    /**
     * 用户登陆
     */
    @RequestMapping("/login")
    @ResponseBody
    public Object login(AccountInfoReq reqInfo) {
        logger.info("登陆请求数据:{}", JSON.toJSONString(reqInfo));
        ApiResponse resp = new ApiResponse();
        try {
            Integer memberId = accountService.login(reqInfo);
            resp.setCode(MeetConstants.API_RET_SUCCESS);
            resp.setMsg("登陆成功");
            resp.setData(memberId);
        } catch (Exception e) {
            logger.error("登陆异常：{}", e.getMessage());
            resp.setCode(MeetConstants.API_RET_FAIL);
            resp.setMsg(e.getMessage());
        }
        return resp;
    }

    /**
     * 获取用户基本信息
     */
    @RequestMapping("/info/{accountId}")
    @ResponseBody
    public Object login(@PathVariable("accountId") Integer accountId) {
        logger.info("获取用户基本信息:{}", accountId);
        ApiResponse resp = new ApiResponse();
        AccountInfo data = accountService.getAccountInfo(accountId);
        resp.setCode(MeetConstants.API_RET_SUCCESS);
        resp.setMsg("数据获取成功");
        resp.setData(data);
        return resp;
    }

    /**
     * 更新用户基本信息
     */
    @RequestMapping("/modify")
    @ResponseBody
    public Object modifyMember(AccountInfoReq reqInfo) {
        logger.info("更新用户基本信息:{}", JSON.toJSONString(reqInfo));
        ApiResponse resp = new ApiResponse();
        try {
            accountService.modifyAccountInfo(reqInfo);
            resp.setCode(MeetConstants.API_RET_SUCCESS);
            resp.setMsg("信息更新成功");
        } catch (Exception e) {
            logger.error("更新用户基本信息失败：{}", e.getMessage());
            e.printStackTrace();
            resp.setCode(MeetConstants.API_RET_FAIL);
            resp.setMsg("信息更新失败");
        }
        return resp;
    }

    /**
     * 找回密码
     */
    @RequestMapping("/backPass")
    @ResponseBody
    public Object backPass(AccountInfoReq reqInfo) {
        logger.info("更新用户密码信息:{}", JSON.toJSONString(reqInfo));
        ApiResponse resp = new ApiResponse();
        try {
            accountService.backPassword((reqInfo));
            resp.setCode(MeetConstants.API_RET_SUCCESS);
            resp.setMsg("密码找回成功");
        } catch (Exception e) {
            logger.error("更新用户密码信息失败：{}", e.getMessage());
            e.printStackTrace();
            resp.setCode(MeetConstants.API_RET_FAIL);
            resp.setMsg(e.getMessage());
        }
        return resp;
    }

    /**
     * 用户更改密码
     */
    @RequestMapping("/editPass")
    @ResponseBody
    public Object editPass(AccountInfoReq reqInfo) {
        logger.info("更新用户密码信息:{}", JSON.toJSONString(reqInfo));
        ApiResponse resp = new ApiResponse();
        try {
            accountService.modifyPassword((reqInfo));
            resp.setCode(MeetConstants.API_RET_SUCCESS);
            resp.setMsg("密码更新成功");
        } catch (Exception e) {
            logger.error("更新用户密码信息失败：{}", e.getMessage());
            e.printStackTrace();
            resp.setCode(MeetConstants.API_RET_FAIL);
            resp.setMsg(e.getMessage());
        }
        return resp;
    }

}