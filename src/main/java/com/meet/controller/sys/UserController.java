package com.meet.controller.sys;

import com.meet.common.constants.MeetConstants;
import com.meet.common.log.LogAnnotation;
import com.meet.dto.rsp.ApiResponse;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.meet.common.resources.Resources;
import com.meet.controller.BaseController;
import com.meet.dto.req.sys.SysUserInfoReq;
import com.meet.orm.service.sys.RoleService;
import com.meet.orm.service.sys.UserService;

@RestController
@RequestMapping("/api/back/user")
public class UserController extends BaseController {
	
	@Autowired
	UserService userService;

	/**
	 * 用户列表
	 */
	@RequestMapping("/page_list")
	@LogAnnotation(module = "用户管理",option = "获取用户列表")
	public Object pageList(SysUserInfoReq userReq) {
		logger.info("访问用户列表");
		ApiResponse resp = new ApiResponse();
		resp.setCode(MeetConstants.SYS_SUCCESS);
		resp.setMsg("数据获取成功");
		resp.setData(userService.findUserListPage(userReq));
		return resp;
	}
	/**
	 * 跳转添加页面
	 */
	@RequestMapping("/info/{userId}")
	@LogAnnotation(module = "用户管理",option = "获取用户信息")
	public Object info(@PathVariable("userId") Integer userId) {
		ApiResponse resp = new ApiResponse();
		resp.setCode(MeetConstants.SYS_SUCCESS);
		resp.setMsg("数据获取成功");
		resp.setData(userService.selectUserByUserId(userId));
		return resp;
	}
	/**
	 * 添加用戶
	 */
	@RequestMapping("/add")
	@LogAnnotation(module = "用户管理",option = "添加用户")
	public Object add(SysUserInfoReq userInfo){
		logger.info("添加系統用戶：{}",JSON.toJSONString(userInfo));
		ApiResponse resp=new ApiResponse();
		try{
			userService.saveUser(userInfo);
			resp.setCode(MeetConstants.SYS_SUCCESS);
			resp.setMsg("用户添加成功");
		}catch(Exception e){
			logger.error("添加用户失败:{}",e.getMessage());
			e.printStackTrace();
			resp.setCode(MeetConstants.SYS_FAILE);
			resp.setMsg(e.getMessage());
		}
		return resp;
	}
	/**
	 * 编辑用户
	 */
	@RequestMapping("/edit")
	@LogAnnotation(module = "用户管理",option = "更新用户信息")
	public Object edit(SysUserInfoReq userInfo){
		logger.info("更新系統用戶：{}",JSON.toJSONString(userInfo));
		ApiResponse resp=new ApiResponse();
		try{
			userService.updateUser(userInfo);
			resp.setCode(MeetConstants.SYS_SUCCESS);
			resp.setMsg("用户更新成功");
		}catch(Exception e){
			logger.error("更新用户失败:{}",e.getMessage());
			e.printStackTrace();
			resp.setCode(MeetConstants.SYS_FAILE);
			resp.setMsg(e.getMessage());
		}
		return resp;
	}
	/**
	 * 设置角色
	 */
	@RequestMapping("/setRole")
	@LogAnnotation(module = "用户管理",option = "为用户设置角色")
	public Object setRole(SysUserInfoReq userInfo){
		logger.info("更新系統用戶角色：{}",JSON.toJSONString(userInfo));
		ApiResponse resp=new ApiResponse();
		try{
			userService.setRole(userInfo);
			resp.setCode(MeetConstants.SYS_SUCCESS);
			resp.setMsg("用戶角色更新成功");
		}catch(Exception e){
			logger.error("更新用戶角色失败:{}",e.getMessage());
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
	@LogAnnotation(module = "用户管理",option = "删除用户")
	public Object del(@PathVariable("id")int id){
		logger.info("刪除用戶編號：{}",id);
		ApiResponse resp=new ApiResponse();
		try{
			userService.delUser(id);
			resp.setCode(MeetConstants.SYS_SUCCESS);
			resp.setMsg("用户删除成功");
		}catch(Exception e){
			logger.error("删除用户失败:{}",e.getMessage());
			e.printStackTrace();
			resp.setCode(MeetConstants.SYS_FAILE);
			resp.setMsg(e.getMessage());
		}
		return resp;
	}
}
