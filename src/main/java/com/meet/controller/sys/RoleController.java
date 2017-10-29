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
import com.meet.dto.req.sys.SysRoleInfoReq;
import com.meet.dto.rsp.ApiResponse;
import com.meet.orm.service.sys.RoleService;

@RestController
@RequestMapping("/role")
public class RoleController extends BaseController {

	@Autowired
	private RoleService roleService;

	/**
	 * 用户列表
	 */
	@RequestMapping("/index")
	public ModelAndView list(SysRoleInfoReq roleReq) {
		logger.info("访问角色列表");
		ModelAndView view = new ModelAndView();
		view.addObject("data", roleService.findRoleListPage(roleReq));
		view.addObject("reqIno", roleReq.getName());
		view.setViewName("role/index");
		return view;
	}

	/**
	 * 跳转添加角色页面
	 */
	@RequestMapping("/addInfo")
	public ModelAndView addInfo() {
		return new ModelAndView("role/add");
	}

	/**
	 * 添加用户角色
	 */
	@RequestMapping("/add")
	public Object save(SysRoleInfoReq roleReq) {
		logger.info("添加系统角色：{}", JSON.toJSONString(roleReq));
		ApiResponse response = new ApiResponse();
		try {
			roleService.addRole(roleReq);
			response.setCode(OrderConstants.SYS_SUCCESS);
			response.setMsg(Resources.getMessage("role_add_success"));
		} catch (Exception e) {
			logger.error("角色添加失败:{}", e.getMessage());
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
		logger.info("刪除角色：{}", id);
		ApiResponse response = new ApiResponse();
		try {
			roleService.delRole(id);
			response.setCode(OrderConstants.SYS_SUCCESS);
			response.setMsg(Resources.getMessage("role_del_success"));
		} catch (Exception e) {
			logger.error("角色删除失败:{}", e.getMessage());
			e.printStackTrace();
			response.setCode(OrderConstants.SYS_FAILE);
			response.setMsg(e.getMessage());
		}
		return response;
	}

	/**
	 * 给角色分配权限
	 */
	@RequestMapping("/moduleInfo/{id}")
	public ModelAndView moduleInfo(@PathVariable("id") int id) {
		ModelAndView view = new ModelAndView();
		view.addObject("ownModules", roleService.getRolePermission(id));
		view.addObject("moduleList", roleService.getAllPermission());
		view.addObject("roleId", id);
		view.setViewName("role/addRoleModule");
		return view;
	}
	/**
	 * 给角色分配权限
	 */
	@RequestMapping("/addRoleModule")
	public Object addRoleModule(SysRoleInfoReq roleReq) {
		logger.info("角色分配权限：{}", JSON.toJSONString(roleReq));
		ApiResponse response=new ApiResponse();
		try{
			roleService.addRolePermission(roleReq);
			response.setCode(OrderConstants.SYS_SUCCESS);
			response.setMsg(Resources.getMessage("role_appy_success"));
		}catch(Exception e){
			logger.error("分配失败:{}",e.getMessage());
			e.printStackTrace();
			response.setCode(OrderConstants.SYS_FAILE);
			response.setMsg(e.getMessage());
		}
		return response;
	}

}
