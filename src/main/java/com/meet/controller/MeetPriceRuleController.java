package com.meet.controller;

import com.alibaba.fastjson.JSON;
import com.meet.common.constants.MeetConstants;
import com.meet.common.log.LogAnnotation;
import com.meet.dto.req.MeetRulePriceReq;
import com.meet.dto.req.MeetRulePriceReq;
import com.meet.dto.rsp.ApiResponse;
import com.meet.orm.service.MeetPriceRulesService;
import com.meet.orm.service.MeetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by bzhx on 2017-11-04 17:39.
 */
@RestController
@RequestMapping("/api/meetRule")
public class MeetPriceRuleController extends BaseController {

    @Autowired
    private MeetPriceRulesService meetPriceRulesService;

    /**
     * 会议室计价规则列表
     */
    @RequestMapping("/page_list")
    @LogAnnotation(module = "会议室计价规则管理",option = "获取会议室计价规则列表")
    public Object pageList(MeetRulePriceReq reqInfo) {
        logger.info("访问会议室计价规则列表");
        ApiResponse resp=new ApiResponse();
        resp.setCode(MeetConstants.SYS_SUCCESS);
        resp.setMsg("数据获取成功");
        resp.setData(meetPriceRulesService.findMeetPriceRuleListPage(reqInfo));
        return resp;
    }
    /**
     * 某个会议室计价规则列表
     */
    @RequestMapping("/list/{meetNo}")
    @LogAnnotation(module = "会议室计价规则管理",option = "获取某个会议室计价规则列表")
    public Object list(@PathVariable("meetNo") String meetNo) {
        logger.info("访问会议室计价规则列表");
        ApiResponse resp=new ApiResponse();
        resp.setCode(MeetConstants.SYS_SUCCESS);
        resp.setMsg("数据获取成功");
        resp.setData(meetPriceRulesService.getMeetPriceRuleByMeetNo(meetNo));
        return resp;
    }
    /**
     * 添加会议室计价规则
     */
    @RequestMapping("/add")
    @LogAnnotation(module = "会议室计价规则管理",option = "添加会议室计价规则")
    public Object add(MeetRulePriceReq reqInfo) {
        logger.info("添加系统会议室计价规则：{}", JSON.toJSONString(reqInfo));
        ApiResponse resp = new ApiResponse();
        try {
            meetPriceRulesService.addMeetPriceRule(reqInfo);
            resp.setCode(MeetConstants.SYS_SUCCESS);
            resp.setMsg("会议室计价规则添加成功");
        } catch (Exception e) {
            logger.error("会议室计价规则添加失败:{}", e.getMessage());
            e.printStackTrace();
            resp.setCode(MeetConstants.SYS_FAILE);
            resp.setMsg(e.getMessage());
        }
        return resp;
    }
    /**
     * 添加会议室计价规则
     */
    @RequestMapping("/edit")
    @LogAnnotation(module = "会议室计价规则管理",option = "更新会议室计价规则")
    public Object edit(MeetRulePriceReq reqInfo) {
        logger.info("更新系统会议室计价规则：{}", JSON.toJSONString(reqInfo));
        ApiResponse resp = new ApiResponse();
        try {
            meetPriceRulesService.updateMeetPriceRule(reqInfo);
            resp.setCode(MeetConstants.SYS_SUCCESS);
            resp.setMsg("会议室计价规则更新成功");
        } catch (Exception e) {
            logger.error("会议室计价规则更新失败:{}", e.getMessage());
            e.printStackTrace();
            resp.setCode(MeetConstants.SYS_FAILE);
            resp.setMsg(e.getMessage());
        }
        return resp;
    }
    /**
     * 刪除会议室计价规则
     */
    @RequestMapping("/del/{id}")
    @LogAnnotation(module = "会议室计价规则管理",option = "删除会议室计价规则")
    public Object del(@PathVariable("id") int id) {
        logger.info("刪除会议室计价规则：{}", id);
        ApiResponse resp = new ApiResponse();
        try {
            meetPriceRulesService.delMeetPriceRule(id);
            resp.setCode(MeetConstants.SYS_SUCCESS);
            resp.setMsg("会议室计价规则删除成功");
        } catch (Exception e) {
            logger.error("会议室删除失败:{}", e.getMessage());
            e.printStackTrace();
            resp.setCode(MeetConstants.SYS_FAILE);
            resp.setMsg(e.getMessage());
        }
        return resp;
    }

}