package com.meet.controller.sys;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.meet.common.constants.OrderConstants;
import com.meet.common.resources.Resources;
import com.meet.controller.BaseController;
import com.meet.dto.req.sys.SysPermissionInfoReq;
import com.meet.dto.rsp.ApiResponse;
import com.meet.orm.service.sys.PermissionService;

/**
 * 权限管理 Created by bzhx on 2017年3月17日 下午3:38:36
 */
@RestController
@RequestMapping("/module")
public class PermissionController extends BaseController {

	@Autowired
	PermissionService permissionService;

	/**
	 * 权限列表
	 */
	@RequestMapping("/index")
	public ModelAndView list(SysPermissionInfoReq permissionReq) {
		logger.info("访问权限列表");
		ModelAndView view = new ModelAndView();
		view.addObject("data", permissionService.findPermissionListPage(permissionReq));
		view.addObject("reqInfo", permissionReq.getName());
		view.setViewName("module/index");
		return view;
	}

	/**
	 * 跳转添加页面
	 */
	@RequestMapping("/addInfo")
	public ModelAndView addInfo() {
		ModelAndView view = new ModelAndView();
		view.addObject("moduleList", permissionService.findParentPermission());
		view.setViewName("module/add");
		return view;
	}
	/**
	 * 添加權限
	 */
	@RequestMapping("/add")
	public Object add(SysPermissionInfoReq permissionReq) {
		ApiResponse response = new ApiResponse();
		try {
			permissionService.addPermission(permissionReq);
			response.setCode(OrderConstants.SYS_SUCCESS);
			response.setMsg(Resources.getMessage("permiss_add_success"));
		} catch (Exception e) {
			logger.error("權限添加失败:{}", e.getMessage());
			e.printStackTrace();
			response.setCode(OrderConstants.SYS_FAILE);
			response.setMsg(e.getMessage());
		}
		return response;
	}

	/**
	 * 跳转編輯页面
	 */
	@RequestMapping("/editInfo/{id}")
	public ModelAndView editInfo(@PathVariable("id") int id) {
		ModelAndView view = new ModelAndView();
		view.addObject("moduleList", permissionService.findParentPermission());
		view.addObject("module", permissionService.findPermissionById(id));
		view.setViewName("module/edit");
		return view;
	}
	@RequestMapping("/edit")
	public Object edit(SysPermissionInfoReq permissionReq) {
		logger.info("更新系统權限：{}", JSON.toJSONString(permissionReq));
		ApiResponse response = new ApiResponse();
		try {
			permissionService.updatePermission(permissionReq);
			response.setCode(OrderConstants.SYS_SUCCESS);
			response.setMsg(Resources.getMessage("permiss_edit_success"));
		} catch (Exception e) {
			logger.error("權限系统失败:{}", e.getMessage());
			e.printStackTrace();
			response.setCode(OrderConstants.SYS_FAILE);
			response.setMsg(e.getMessage());
		}
		return response;
	}
	/**
	 * 刪除用戶
	 */
	@RequestMapping("/del/{id}")
	public Object del(@PathVariable("id") int id) {
		logger.info("刪除權限：{}", id);
		ApiResponse response = new ApiResponse();
		try {
			permissionService.delPermission(id);
			response.setCode(OrderConstants.SYS_SUCCESS);
			response.setMsg(Resources.getMessage("permiss_del_success"));
		} catch (Exception e) {
			logger.error("權限删除失败:{}", e.getMessage());
			e.printStackTrace();
			response.setCode(OrderConstants.SYS_FAILE);
			response.setMsg(e.getMessage());
		}
		return response;
	}
}
