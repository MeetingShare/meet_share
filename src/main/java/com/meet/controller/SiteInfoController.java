package com.meet.controller;

import com.alibaba.fastjson.JSON;
import com.meet.common.constants.MeetConstants;
import com.meet.common.log.LogAnnotation;
import com.meet.dto.req.SiteInfoReq;
import com.meet.dto.rsp.ApiResponse;
import com.meet.orm.service.SiteInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by bzhx on 2017-11-04 14:34.
 */
@RestController
@RequestMapping("/api/site")
public class SiteInfoController extends BaseController{

    @Autowired
    private SiteInfoService siteInfoService;
    /**站点信息
     * 列表分页
     */
    @RequestMapping("/page_list")
    @LogAnnotation(module = "站点信息管理",option = "获取站点信息列表分页")
    public Object pageList(SiteInfoReq reqInfo) {
        logger.info("访问站点信息列表分页");
        ApiResponse resp=new ApiResponse();
        resp.setCode(MeetConstants.SYS_SUCCESS);
        resp.setMsg("数据获取成功");
        resp.setData(siteInfoService.findSiteInfoListPage(reqInfo));
        return resp;
    }

    /**
     * 站点信息列表
     */
    @RequestMapping("/data/{type}")
    @LogAnnotation(module = "站点信息管理",option = "获取站点信息列表")
    public Object data(@PathVariable("type") String type) {
        logger.info("访问站点信息列表");
        ApiResponse resp=new ApiResponse();
        resp.setCode(MeetConstants.SYS_SUCCESS);
        resp.setMsg("数据获取成功");
        resp.setData(siteInfoService.findSiteInfoByType(type));
        return resp;
    }
    /**
     * 添加站点信息
     */
    @RequestMapping("/add")
    @LogAnnotation(module = "站点信息管理",option = "添加站点信息")
    public Object add(SiteInfoReq reqInfo) {
        logger.info("添加系统站点信息：{}", JSON.toJSONString(reqInfo));
        ApiResponse resp = new ApiResponse();
        try {
            siteInfoService.saveSiteInfo(reqInfo);
            resp.setCode(MeetConstants.SYS_SUCCESS);
            resp.setMsg("站点信息添加成功");
        } catch (Exception e) {
            logger.error("站点信息添加失败:{}", e.getMessage());
            e.printStackTrace();
            resp.setCode(MeetConstants.SYS_FAILE);
            resp.setMsg(e.getMessage());
        }
        return resp;
    }

    /**
     * 刪除站点信息
     */
    @RequestMapping("/del/{id}")
    @LogAnnotation(module = "站点信息管理",option = "获取站点信息")
    public Object del(@PathVariable("id") int id) {
        logger.info("刪除站点信息：{}", id);
        ApiResponse resp = new ApiResponse();
        try {
            siteInfoService.delSiteInfo(id);
            resp.setCode(MeetConstants.SYS_SUCCESS);
            resp.setMsg("站点信息删除成功");
        } catch (Exception e) {
            logger.error("站点信息删除失败:{}", e.getMessage());
            e.printStackTrace();
            resp.setCode(MeetConstants.SYS_FAILE);
            resp.setMsg(e.getMessage());
        }
        return resp;
    }
    /**
     * 添加站点信息
     */
    @RequestMapping("/edit")
    @LogAnnotation(module = "站点信息管理",option = "更新站点信息")
    public Object edit(SiteInfoReq reqInfo) {
        logger.info("更新系统站点信息：{}", JSON.toJSONString(reqInfo));
        ApiResponse resp = new ApiResponse();
        try {
            siteInfoService.updateSiteInfo(reqInfo);
            resp.setCode(MeetConstants.SYS_SUCCESS);
            resp.setMsg("站点信息更新成功");
        } catch (Exception e) {
            logger.error("站点信息更新失败:{}", e.getMessage());
            e.printStackTrace();
            resp.setCode(MeetConstants.SYS_FAILE);
            resp.setMsg(e.getMessage());
        }
        return resp;
    }
    /**
     * 获取站点信息
     */
    @RequestMapping("/info/{id}")
    @LogAnnotation(module = "站点信息管理",option = "获取某个站点信息信息")
    public Object info(@PathVariable("id") int id) {
        ApiResponse resp=new ApiResponse();
        resp.setCode(MeetConstants.SYS_SUCCESS);
        resp.setMsg("数据获取成功");
        resp.setData(siteInfoService.selectSiteInfoById(id));
        return resp;
    }
}