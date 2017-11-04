package com.meet.controller;

import com.alibaba.fastjson.JSON;
import com.meet.common.constants.MeetConstants;
import com.meet.common.log.LogAnnotation;
import com.meet.dto.req.SysRoleInfoReq;
import com.meet.dto.rsp.ApiResponse;
import com.meet.orm.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/role")
public class RoleController extends BaseController {

	@Autowired
	private RoleService roleService;

	/**
	 * 角色列表基于分页
	 */
	@RequestMapping("/page_list")
	@LogAnnotation(module = "角色管理",option = "获取角色列表基于分页")
	public Object pageList(SysRoleInfoReq roleReq) {
		logger.info("访问角色列表");
		ApiResponse resp=new ApiResponse();
		resp.setCode(MeetConstants.SYS_SUCCESS);
		resp.setMsg("数据获取成功");
		resp.setData(roleService.findRoleListPage(roleReq));
		return resp;
	}
	/**
	 * 角色列表
	 */
	@RequestMapping("/list")
	@LogAnnotation(module = "角色管理",option = "获取角色列表")
	public Object list() {
		logger.info("访问角色列表");
		ApiResponse resp=new ApiResponse();
		resp.setCode(MeetConstants.SYS_SUCCESS);
		resp.setMsg("数据获取成功");
		resp.setData(roleService.getAllRole());
		return resp;
	}

	/**
	 * 添加角色
	 */
	@RequestMapping("/add")
	@LogAnnotation(module = "角色管理",option = "添加角色")
	public Object add(SysRoleInfoReq roleReq) {
		logger.info("添加系统角色：{}", JSON.toJSONString(roleReq));
		ApiResponse resp = new ApiResponse();
		try {
			roleService.addRole(roleReq);
			resp.setCode(MeetConstants.SYS_SUCCESS);
			resp.setMsg("角色添加成功");
		} catch (Exception e) {
			logger.error("角色添加失败:{}", e.getMessage());
			e.printStackTrace();
			resp.setCode(MeetConstants.SYS_FAILE);
			resp.setMsg(e.getMessage());
		}
		return resp;
	}
	/**
	 * 添加角色
	 */
	@RequestMapping("/edit")
	@LogAnnotation(module = "角色管理",option = "更新角色")
	public Object edit(SysRoleInfoReq roleReq) {
		logger.info("更新系统角色：{}", JSON.toJSONString(roleReq));
		ApiResponse resp = new ApiResponse();
		try {
			roleService.updateRole(roleReq);
			resp.setCode(MeetConstants.SYS_SUCCESS);
			resp.setMsg("角色更新成功");
		} catch (Exception e) {
			logger.error("角色更新失败:{}", e.getMessage());
			e.printStackTrace();
			resp.setCode(MeetConstants.SYS_FAILE);
			resp.setMsg(e.getMessage());
		}
		return resp;
	}
	/**
	 * 刪除角色
	 */
	@RequestMapping("/del/{id}")
	@LogAnnotation(module = "角色管理",option = "获取角色")
	public Object del(@PathVariable("id") int id) {
		logger.info("刪除角色：{}", id);
		ApiResponse resp = new ApiResponse();
		try {
			roleService.delRole(id);
			resp.setCode(MeetConstants.SYS_SUCCESS);
			resp.setMsg("角色删除成功");
		} catch (Exception e) {
			logger.error("角色删除失败:{}", e.getMessage());
			e.printStackTrace();
			resp.setCode(MeetConstants.SYS_FAILE);
			resp.setMsg(e.getMessage());
		}
		return resp;
	}

	/**
	 * 给角色分配权限
	 */
	@RequestMapping("/info/{id}")
	@LogAnnotation(module = "角色管理",option = "获取某个角色信息")
	public Object moduleInfo(@PathVariable("id") int id) {
		ApiResponse resp=new ApiResponse();
		resp.setCode(MeetConstants.SYS_SUCCESS);
		resp.setMsg("数据获取成功");
		resp.setData(roleService.selectByRoleId(id));
		return resp;
	}
	/**
	 * 给角色分配权限
	 */
	@RequestMapping("/addRoleModule")
	@LogAnnotation(module = "角色管理",option = "为角色分配权限")
	public Object addRoleModule(SysRoleInfoReq roleReq) {
		logger.info("角色分配权限：{}", JSON.toJSONString(roleReq));
		ApiResponse resp=new ApiResponse();
		try{
			roleService.addRolePermission(roleReq);
			resp.setCode(MeetConstants.SYS_SUCCESS);
			resp.setMsg("权限分配成功");
		}catch(Exception e){
			logger.error("分配失败:{}",e.getMessage());
			e.printStackTrace();
			resp.setCode(MeetConstants.SYS_FAILE);
			resp.setMsg(e.getMessage());
		}
		return resp;
	}

}
