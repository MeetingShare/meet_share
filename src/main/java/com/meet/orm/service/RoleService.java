package com.meet.orm.service;

import com.github.pagehelper.PageInfo;
import com.meet.dto.req.SysRoleInfoReq;
import com.meet.orm.pojo.SysRole;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface RoleService {
    /**
     * 获取所有角色
     */
	List<SysRole> getAllRole();
	/**
	 * 获取角色列表
	 * @param roleInfo
	 * @return
	 */
	PageInfo<SysRole> findRoleListPage(SysRoleInfoReq roleInfo);
	
	/**
	 * 添加角色
	 */
	void addRole(SysRoleInfoReq roleInfo) throws Exception;
	
	/**
	 * 刪除角色
	 */
	int delRole(int roleId) throws Exception;
	/**
	 * 更新角色
	 */
	int updateRole(SysRoleInfoReq roleInfo) throws Exception;
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

