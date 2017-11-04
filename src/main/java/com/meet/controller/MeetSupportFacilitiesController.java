package com.meet.controller;

import com.alibaba.fastjson.JSON;
import com.meet.common.constants.MeetConstants;
import com.meet.common.log.LogAnnotation;
import com.meet.dto.req.MeetSupportFacilitiesReq;
import com.meet.dto.rsp.ApiResponse;
import com.meet.orm.service.MeetSupportFacilitiesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by bzhx on 2017-11-04 9:49.
 */
@RestController
@RequestMapping("/api/msf")
public class MeetSupportFacilitiesController extends BaseController{

    @Autowired
    private MeetSupportFacilitiesService meetSupportFacilitiesService;

    /**
     * 配套设施列表分页
     */
    @RequestMapping("/page_list")
    @LogAnnotation(module = "主题管理",option = "获取主题列表分页")
    public Object pageList(MeetSupportFacilitiesReq reqInfo) {
        logger.info("访问主题列表分页");
        ApiResponse resp=new ApiResponse();
        resp.setCode(MeetConstants.SYS_SUCCESS);
        resp.setMsg("数据获取成功");
        resp.setData(meetSupportFacilitiesService.findMeetSupportFacilitiesListPage(reqInfo));
        return resp;
    }

    /**
     * 主题列表
     */
    @RequestMapping("/list")
    @LogAnnotation(module = "主题管理",option = "获取主题列表")
    public Object list(MeetSupportFacilitiesReq reqInfo) {
        logger.info("访问主题列表");
        ApiResponse resp=new ApiResponse();
        resp.setCode(MeetConstants.SYS_SUCCESS);
        resp.setMsg("数据获取成功");
        resp.setData(meetSupportFacilitiesService.findMeetSupportFacilitiesList(reqInfo));
        return resp;
    }
    /**
     * 添加主题
     */
    @RequestMapping("/add")
    @LogAnnotation(module = "主题管理",option = "添加主题")
    public Object add(MeetSupportFacilitiesReq reqInfo) {
        logger.info("添加系统主题：{}", JSON.toJSONString(reqInfo));
        ApiResponse resp = new ApiResponse();
        try {
            meetSupportFacilitiesService.saveData(reqInfo);
            resp.setCode(MeetConstants.SYS_SUCCESS);
            resp.setMsg("主题添加成功");
        } catch (Exception e) {
            logger.error("主题添加失败:{}", e.getMessage());
            e.printStackTrace();
            resp.setCode(MeetConstants.SYS_FAILE);
            resp.setMsg(e.getMessage());
        }
        return resp;
    }

    /**
     * 刪除主题
     */
    @RequestMapping("/del/{id}")
    @LogAnnotation(module = "主题管理",option = "获取主题")
    public Object del(@PathVariable("id") int id) {
        logger.info("刪除主题：{}", id);
        ApiResponse resp = new ApiResponse();
        try {
            meetSupportFacilitiesService.delData(id);
            resp.setCode(MeetConstants.SYS_SUCCESS);
            resp.setMsg("主题删除成功");
        } catch (Exception e) {
            logger.error("主题删除失败:{}", e.getMessage());
            e.printStackTrace();
            resp.setCode(MeetConstants.SYS_FAILE);
            resp.setMsg(e.getMessage());
        }
        return resp;
    }
    /**
     * 添加主题
     */
    @RequestMapping("/edit")
    @LogAnnotation(module = "主题管理",option = "更新主题")
    public Object edit(MeetSupportFacilitiesReq reqInfo) {
        logger.info("更新系统主题：{}", JSON.toJSONString(reqInfo));
        ApiResponse resp = new ApiResponse();
        try {
            meetSupportFacilitiesService.updateData(reqInfo);
            resp.setCode(MeetConstants.SYS_SUCCESS);
            resp.setMsg("主题更新成功");
        } catch (Exception e) {
            logger.error("主题更新失败:{}", e.getMessage());
            e.printStackTrace();
            resp.setCode(MeetConstants.SYS_FAILE);
            resp.setMsg(e.getMessage());
        }
        return resp;
    }
    /**
     * 给主题分配权限
     */
    @RequestMapping("/info/{id}")
    @LogAnnotation(module = "主题管理",option = "获取某个主题信息")
    public Object info(@PathVariable("id") int id) {
        ApiResponse resp=new ApiResponse();
        resp.setCode(MeetConstants.SYS_SUCCESS);
        resp.setMsg("数据获取成功");
        resp.setData(meetSupportFacilitiesService.selectInfoById(id));
        return resp;
    }
}