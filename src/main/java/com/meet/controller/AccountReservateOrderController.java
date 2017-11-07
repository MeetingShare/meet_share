package com.meet.controller;

import com.alibaba.fastjson.JSON;
import com.meet.common.constants.MeetConstants;
import com.meet.common.log.LogAnnotation;
import com.meet.dto.req.AccountInfoReq;
import com.meet.dto.req.AccountReservateOrderReq;
import com.meet.dto.req.MeetingInfoReq;
import com.meet.dto.rsp.ApiResponse;
import com.meet.orm.service.AccountReservateOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by bzhx on 2017-11-07 20:13.
 */
@RestController
@RequestMapping("/api/order")
public class AccountReservateOrderController extends BaseController {

    @Autowired
    private AccountReservateOrderService accountReservateOrderService;
    /**
     * 用户预定订单列表
     */
    @RequestMapping("/page_list")
    public Object pageList(AccountReservateOrderReq reqInfo) {
        logger.info("访问用户预定订单列表");
        ApiResponse resp = new ApiResponse();
        resp.setCode(MeetConstants.SYS_SUCCESS);
        resp.setMsg("数据获取成功");
        resp.setData(accountReservateOrderService.findReservateOrderListPage(reqInfo));
        return resp;
    }
    /**
     * 用户预定订单详细信息
     */
    @RequestMapping("/info/{id}")
    public Object pageList(@PathVariable("id")Integer id) {
        logger.info("访问用户订单详细");
        ApiResponse resp = new ApiResponse();
        resp.setCode(MeetConstants.SYS_SUCCESS);
        resp.setMsg("数据获取成功");
        resp.setData(accountReservateOrderService.findOrderInfo(id));
        return resp;
    }
    /**
     * 添加会议室
     */
    @RequestMapping("/reservate")
    @LogAnnotation(module = "订单管理",option = "用户预定会议室")
    public Object reservate(AccountReservateOrderReq reqInfo) {
        logger.info("用户预定会议室：{}", JSON.toJSONString(reqInfo));
        ApiResponse resp = new ApiResponse();
        try {
            accountReservateOrderService.reservate(reqInfo);
            resp.setCode(MeetConstants.SYS_SUCCESS);
            resp.setMsg("会议室预定成功");
        } catch (Exception e) {
            logger.error("会议室预定失败:{}", e.getMessage());
            e.printStackTrace();
            resp.setCode(MeetConstants.SYS_FAILE);
            resp.setMsg(e.getMessage());
        }
        return resp;
    }
}