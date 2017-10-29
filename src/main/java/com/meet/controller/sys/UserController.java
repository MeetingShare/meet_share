package com.meet.controller.sys;

import com.meet.dto.rsp.ApiResponse;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.meet.common.constants.OrderConstants;
import com.meet.common.resources.Resources;
import com.meet.controller.BaseController;
import com.meet.dto.req.sys.SysUserInfoReq;
import com.meet.dto.rsp.ApiResponse;
import com.meet.orm.service.sys.RoleService;
import com.meet.orm.service.sys.UserService;

@RestController
@RequestMapping("/admin")
public class UserController extends BaseController {
	
	@Autowired
	UserService userService;
	@Autowired
	private RoleService roleService;


	/**
	 * 用户列表
	 */
	@RequestMapping("/index")
	public ModelAndView list(SysUserInfoReq userReq) {
		logger.info("访问用户列表");
		ModelAndView view = new ModelAndView();
		view.addObject("data", userService.findUserListPage(userReq));
		view.addObject("reqInfo", userReq);
		view.setViewName("user/index");
		return view;
	}
	/**
	 * 跳转添加页面
	 */
	@RequestMapping("/addInfo")
	public ModelAndView addInfo() {
		ModelAndView view = new ModelAndView();
		view.addObject("roleList",roleService.getAllRole());
		view.setViewName("user/add");
		return view;
	}
	/**
	 * 添加用戶
	 */
	@RequestMapping("/save")
	public Object save(SysUserInfoReq userInfo){
		logger.info("添加系統用戶：{}",JSON.toJSONString(userInfo));
		ApiResponse response=new ApiResponse();
		try{
			userService.saveUser(userInfo);
			response.setCode(OrderConstants.SYS_SUCCESS);
			response.setMsg(Resources.getMessage("user_add_success"));
		}catch(Exception e){
			logger.error("添加用户失败:{}",e.getMessage());
			e.printStackTrace();
			response.setCode(OrderConstants.SYS_FAILE);
			response.setMsg(e.getMessage());
		}
		return response;
	}
	/**
	 * 跳转更新页面
	 */
	@RequestMapping("/modifyInfo/{id}")
	public ModelAndView modifyInfo(@PathVariable("id")int id){
		ModelAndView view=new ModelAndView();
		view.addObject("roleList",roleService.getAllRole());
		view.addObject("info", userService.selectUserByUserId(id));
		view.setViewName("user/edit");
		return view;
	}
	/**
	 * 编辑用户
	 */
	@RequestMapping("/modify")
	public Object modify(SysUserInfoReq userInfo){
		logger.info("更新系統用戶：{}",JSON.toJSONString(userInfo));
		ApiResponse response=new ApiResponse();
		try{
			userService.updateUser(userInfo);
			response.setCode(OrderConstants.SYS_SUCCESS);
			response.setMsg(Resources.getMessage("user_edit_success"));
		}catch(Exception e){
			logger.error("更新用户失败:{}",e.getMessage());
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
	public Object del(@PathVariable("id")int id){
		logger.info("刪除用戶編號：{}",id);
		ApiResponse response=new ApiResponse();
		try{
			userService.delUser(id);
			response.setCode(OrderConstants.SYS_SUCCESS);
			response.setMsg(Resources.getMessage("user_del_success"));
		}catch(Exception e){
			logger.error("删除用户失败:{}",e.getMessage());
			e.printStackTrace();
			response.setCode(OrderConstants.SYS_FAILE);
			response.setMsg(e.getMessage());
		}
		return response;
	}
	
	/**
	 * 跳转用户更新密码
	 */
	@RequestMapping("/toEditPass")
	public ModelAndView toEditPass(){
		ModelAndView view=new ModelAndView();
		Integer userId = (int) getShiroSession(OrderConstants.SESSION_USER_Id);
		view.addObject("info",userService.selectUserByUserId(userId));
		view.setViewName("edit_pass");
		return view;
	}
	/**
	 * 用户更新密码
	 */
	@RequestMapping("/editPass")
	public Object editPass(SysUserInfoReq userInfo){
		logger.info("更新系統用戶密码：{}",JSON.toJSONString(userInfo));
		ApiResponse response=new ApiResponse();
		try{
			userService.editPass(userInfo);
			response.setCode(OrderConstants.SYS_SUCCESS);
			response.setMsg(Resources.getMessage("user_edit_pass_success"));
			SecurityUtils.getSubject().logout();
		}catch(Exception e){
			logger.error("密码更新失败:{}",e.getMessage());
			e.printStackTrace();
			response.setCode(OrderConstants.SYS_FAILE);
			response.setMsg(e.getMessage());
		}
		return response;
	}
}
