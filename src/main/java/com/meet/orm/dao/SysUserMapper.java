package com.meet.orm.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.meet.orm.pojo.SysPermission;
import com.meet.orm.pojo.SysUser;

public interface SysUserMapper {
	
	int insertSelective(SysUser record);
	
	/**
	 * 获取用户菜单
	 * @param userId
	 * @return
	 */
	List<SysPermission> findUserMenu(Integer userId);
	/**
	 * 获取用户信息
	 * @param name
	 * @return
	 */
	SysUser findUserByName(String name);
	
	List<SysUser> findUserListPage(@Param("username")String username);
	
	/**
	 * 刪除用戶
	 */
	int delUserById(int userId);
	/**
	 * 获取用户信息
	 */
	SysUser selectUserById(int userId);
	
	int updateByPrimaryKeySelective(SysUser record);
}