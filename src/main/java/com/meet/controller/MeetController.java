package com.meet.controller;

import com.alibaba.fastjson.JSON;
import com.meet.common.constants.MeetConstants;
import com.meet.common.log.LogAnnotation;
import com.meet.dto.req.MeetingInfoReq;
import com.meet.dto.rsp.ApiResponse;
import com.meet.orm.service.MeetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by bzhx on 2017-11-04 9:49.
 */
@RestController
@RequestMapping("/api/meet")
public class MeetController extends BaseController{

    @Autowired
    private MeetService meetService;

    /**
     * 会议室列表
     */
    @RequestMapping("/page_list")
    @LogAnnotation(module = "会议室管理",option = "获取会议室列表")
    public Object pageList(MeetingInfoReq reqInfo) {
        logger.info("访问会议室列表");
        ApiResponse resp=new ApiResponse();
        resp.setCode(MeetConstants.SYS_SUCCESS);
        resp.setMsg("数据获取成功");
        resp.setData(meetService.findMeetListPage(reqInfo));
        return resp;
    }
    /**
     * 添加会议室
     */
    @RequestMapping("/add")
    @LogAnnotation(module = "会议室管理",option = "添加会议室")
    public Object add(MeetingInfoReq reqInfo) {
        logger.info("添加系统会议室：{}", JSON.toJSONString(reqInfo));
        ApiResponse resp = new ApiResponse();
        try {
            meetService.addMeet(reqInfo);
            resp.setCode(MeetConstants.SYS_SUCCESS);
            resp.setMsg("会议室添加成功");
        } catch (Exception e) {
            logger.error("会议室添加失败:{}", e.getMessage());
            e.printStackTrace();
            resp.setCode(MeetConstants.SYS_FAILE);
            resp.setMsg(e.getMessage());
        }
        return resp;
    }
    /**
     * 更新会议室
     */
    @RequestMapping("/edit")
    @LogAnnotation(module = "会议室管理",option = "更新会议室")
    public Object edit(MeetingInfoReq reqInfo) {
        logger.info("更新系统会议室：{}", JSON.toJSONString(reqInfo));
        ApiResponse resp = new ApiResponse();
        try {
            meetService.updateMeet(reqInfo);
            resp.setCode(MeetConstants.SYS_SUCCESS);
            resp.setMsg("会议室更新成功");
        } catch (Exception e) {
            logger.error("会议室更新失败:{}", e.getMessage());
            e.printStackTrace();
            resp.setCode(MeetConstants.SYS_FAILE);
            resp.setMsg(e.getMessage());
        }
        return resp;
    }
    /**
     * 刪除会议室
     */
    @RequestMapping("/del/{id}")
    @LogAnnotation(module = "会议室管理",option = "获取会议室")
    public Object del(@PathVariable("id") int id) {
        logger.info("刪除会议室：{}", id);
        ApiResponse resp = new ApiResponse();
        try {
            meetService.delMeet(id);
            resp.setCode(MeetConstants.SYS_SUCCESS);
            resp.setMsg("会议室删除成功");
        } catch (Exception e) {
            logger.error("会议室删除失败:{}", e.getMessage());
            e.printStackTrace();
            resp.setCode(MeetConstants.SYS_FAILE);
            resp.setMsg(e.getMessage());
        }
        return resp;
    }

    /**
     * 获取某个会议室详细信息
     */
    @RequestMapping("/info/{id}")
    @LogAnnotation(module = "会议室管理",option = "获取某个会议室详细信息")
    public Object moduleInfo(@PathVariable("id")Integer id) {
        ApiResponse resp = new ApiResponse();
        resp.setCode(MeetConstants.SYS_SUCCESS);
        resp.setMsg("数据获取成功");
        resp.setData(meetService.getMeetById(id));
        return resp;
    }
}