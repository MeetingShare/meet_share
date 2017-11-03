package com.meet.orm.service.sys;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageInfo;
import com.meet.dto.req.sys.SysUserInfoReq;
import com.meet.orm.pojo.SysPermission;
import com.meet.orm.pojo.SysUser;

/**
 * Created by bzhx on 2017年2月23日 下午5:13:52
 */
public interface UserService {
	/**
	 * 获取用户菜单
	 * 
	 * @param userId
	 * @return
	 */
	List<SysPermission> findUserMenu(Integer userId);

	/**
	 * 获取用户信息
	 * 
	 * @param name
	 * @return
	 */
	SysUser findUserByName(String name);

	/**
	 * 获取用户列表
	 * 
	 * @param userInfo
	 * @return
	 */
	PageInfo<SysUser> findUserListPage(SysUserInfoReq userInfo);

	/**
	 * 添加用户
	 * 
	 * @param userInfo
	 * @return
	 * @throws Exception
	 */
	@Transactional(rollbackFor=Exception.class)
	SysUser saveUser(SysUserInfoReq userInfo) throws Exception;

	/**
	 * 刪除用戶
	 */
	@Transactional(rollbackFor=Exception.class)
	int delUser(int userId) throws Exception;

	/**
	 * 更新用戶
	 */
	@Transactional(rollbackFor=Exception.class)
	void updateUser(SysUserInfoReq userInfo) throws Exception;

	/**
	 * 获取用户根据用户编号
	 */
	SysUser selectUserByUserId(int userId);
	/**
	 * 更新用户密码
	 * @param userInfo
	 * @throws Exception
	 */
	@Transactional(rollbackFor=Exception.class)
	void editPass(SysUserInfoReq userInfo) throws Exception;

	/**
	 * 设置角色
	 * @param userInfo
	 * @throws Exception
	 */
	@Transactional(rollbackFor=Exception.class)
	void setRole(SysUserInfoReq userInfo)throws Exception;

	/**
	 * 用户登录
	 * @param userInfoReq
	 * @throws Exception
	 */
	SysUser login(SysUserInfoReq userInfoReq) throws  Exception;
}
