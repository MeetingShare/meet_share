package com.meet.controller;

import com.alibaba.fastjson.JSON;
import com.meet.common.constants.MeetConstants;
import com.meet.common.log.LogAnnotation;
import com.meet.dto.req.AccountFeedbackInfoReq;
import com.meet.dto.rsp.ApiResponse;
import com.meet.orm.service.AccountFeedbackInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by bzhx on 2017-11-04 9:49.
 */
@RestController
@RequestMapping("/api/feeback")
public class AccountFeebackInfoController extends BaseController{

    @Autowired
    private AccountFeedbackInfoService acountFeebackInfoService;

    /**
     * 意见反馈列表分页
     */
    @RequestMapping("/page_list")
    @LogAnnotation(module = "意见反馈管理",option = "获取意见反馈列表分页")
    public Object pageList(AccountFeedbackInfoReq reqInfo) {
        logger.info("访问意见反馈列表分页");
        ApiResponse resp=new ApiResponse();
        resp.setCode(MeetConstants.SYS_SUCCESS);
        resp.setMsg("数据获取成功");
        resp.setData(acountFeebackInfoService.findAccountFeedbackInfoListPage(reqInfo));
        return resp;
    }
    /**
     * 处理意见反馈
     */
    @RequestMapping("/edit")
    @LogAnnotation(module = "意见反馈管理",option = "更新意见反馈")
    public Object edit(AccountFeedbackInfoReq reqInfo) {
        logger.info("更新系统意见反馈：{}", JSON.toJSONString(reqInfo));
        ApiResponse resp = new ApiResponse();
        try {
            acountFeebackInfoService.updateAccountFeedbackInfo(reqInfo);
            resp.setCode(MeetConstants.SYS_SUCCESS);
            resp.setMsg("意见反馈更新成功");
        } catch (Exception e) {
            logger.error("意见反馈更新失败:{}", e.getMessage());
            e.printStackTrace();
            resp.setCode(MeetConstants.SYS_FAILE);
            resp.setMsg(e.getMessage());
        }
        return resp;
    }
}