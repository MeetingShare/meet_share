package com.meet.orm.service.sys;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageInfo;
import com.meet.dto.req.sys.SysRoleInfoReq;
import com.meet.orm.pojo.SysPermission;
import com.meet.orm.pojo.SysRole;
import com.meet.orm.pojo.SysRolePermission;

public interface RoleService {
    /**
     * 获取所有角色
     */
	List<SysRole> getAllRole();
	/**
	 * 获取角色列表
	 * 
	 * @param userBean
	 * @return
	 */
	PageInfo<SysRole> findRoleListPage(SysRoleInfoReq roleInfo);
	
	/**
	 * 添加角色
	 */
	@Transactional(rollbackFor=Exception.class)
	void addRole(SysRoleInfoReq roleInfo) throws Exception;
	
	/**
	 * 刪除角色
	 */
	@Transactional(rollbackFor=Exception.class)
	int delRole(int roleId) throws Exception;
	
	/**
	 * 获取所有权限
	 */
	List<SysPermission> getAllPermission();
	/**
	 * 获取角色权限
	 */
	List<SysRolePermission> getRolePermission(int roleId);
	/**
	 * 添加角色分配的权限
	 */
	@Transactional(rollbackFor=Exception.class)
	void addRolePermission(SysRoleInfoReq roleInfo) throws Exception;

	/**
	 * 获取某个角色信息
	 */
	SysRole selectByRoleId(Integer roleId);
}

