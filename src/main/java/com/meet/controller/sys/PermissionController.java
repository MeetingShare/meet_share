package com.meet.controller.sys;

import com.alibaba.fastjson.JSON;
import com.meet.common.constants.MeetConstants;
import com.meet.common.log.LogAnnotation;
import com.meet.controller.BaseController;
import com.meet.dto.req.sys.SysPermissionInfoReq;
import com.meet.dto.rsp.ApiResponse;
import com.meet.orm.service.sys.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * 权限管理 Created by bzhx on 2017年3月17日 下午3:38:36
 */
@RestController
@RequestMapping("/api/back/module")
public class PermissionController extends BaseController {

	@Autowired
	PermissionService permissionService;

	/**
	 * 权限列表
	 */
	@RequestMapping("/page_list")
	@LogAnnotation(module = "权限管理",option = "获取权限列表基于分页")
	public Object list(SysPermissionInfoReq permissionReq) {
		logger.info("访问权限列表");
		ApiResponse resp=new ApiResponse();
		resp.setCode(MeetConstants.SYS_SUCCESS);
		resp.setMsg("数据获取成功");
		resp.setData(permissionService.findPermissionListPage(permissionReq));
		return resp;
	}

	/**
	 * 获取所有权限不分页
	 */
	@RequestMapping("/list")
	@LogAnnotation(module = "权限管理",option = "获取权限列表")
	public Object addInfo() {
		ApiResponse resp=new ApiResponse();
		resp.setCode(MeetConstants.SYS_SUCCESS);
		resp.setMsg("数据获取成功");
		resp.setData(permissionService.findParentPermission());
		return resp;
	}
	/**
	 * 添加權限
	 */
	@RequestMapping("/add")
	@LogAnnotation(module = "权限管理",option = "添加权限")
	public Object add(SysPermissionInfoReq permissionReq) {
		ApiResponse resp = new ApiResponse();
		try {
			permissionService.addPermission(permissionReq);
			resp.setCode(MeetConstants.SYS_SUCCESS);
			resp.setMsg("权限添加成功");
		} catch (Exception e) {
			logger.error("權限添加失败:{}", e.getMessage());
			e.printStackTrace();
			resp.setCode(MeetConstants.SYS_FAILE);
			resp.setMsg(e.getMessage());
		}
		return resp;
	}

	/**
	 * 获取某个权限信息
	 */
	@RequestMapping("/info/{id}")
	@LogAnnotation(module = "权限管理",option = "获取权限详细信息")
	public Object info(@PathVariable("id") int id) {
		ApiResponse resp=new ApiResponse();
		resp.setCode(MeetConstants.SYS_SUCCESS);
		resp.setMsg("数据获取成功");
		resp.setData(permissionService.findPermissionById(id));
		return resp;
	}
	@RequestMapping("/edit")
	@LogAnnotation(module = "权限管理",option = "更新某个权限")
	public Object edit(SysPermissionInfoReq permissionReq) {
		logger.info("更新系统權限：{}", JSON.toJSONString(permissionReq));
		ApiResponse resp = new ApiResponse();
		try {
			permissionService.updatePermission(permissionReq);
			resp.setCode(MeetConstants.SYS_SUCCESS);
			resp.setMsg("权限更新成功");
		} catch (Exception e) {
			logger.error("權限系统失败:{}", e.getMessage());
			e.printStackTrace();
			resp.setCode(MeetConstants.SYS_FAILE);
			resp.setMsg(e.getMessage());
		}
		return resp;
	}
	/**
	 * 刪除用戶
	 */
	@RequestMapping("/del/{id}")
	@LogAnnotation(module = "权限管理",option = "删除权限")
	public Object del(@PathVariable("id") int id) {
		logger.info("刪除權限：{}", id);
		ApiResponse resp = new ApiResponse();
		try {
			permissionService.delPermission(id);
			resp.setCode(MeetConstants.SYS_SUCCESS);
			resp.setMsg("权限删除成功");
		} catch (Exception e) {
			logger.error("權限删除失败:{}", e.getMessage());
			e.printStackTrace();
			resp.setCode(MeetConstants.SYS_FAILE);
			resp.setMsg(e.getMessage());
		}
		return resp;
	}
}
