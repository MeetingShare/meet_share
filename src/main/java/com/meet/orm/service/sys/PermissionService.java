package com.meet.orm.service.sys;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageInfo;
import com.meet.dto.req.sys.SysPermissionInfoReq;
import com.meet.orm.pojo.SysPermission;

/**
 * 权限底层接口 Created by bzhx on 2017年3月17日 下午3:44:23
 */
public interface PermissionService {
	/**
	 * 权限列表分页
	 * @return
	 */
	PageInfo<SysPermission> findPermissionListPage(SysPermissionInfoReq permissionReq);

	/**
	 * 添加角色
	 */
	@Transactional(rollbackFor=Exception.class)
	void addPermission(SysPermissionInfoReq permission) throws Exception;

	/**
	 * 刪除角色
	 */
	@Transactional(rollbackFor=Exception.class)
	void delPermission(int permissionId) throws Exception;
	
	/**
	 * 更新權限
	 */
	@Transactional(rollbackFor=Exception.class)
	void updatePermission(SysPermissionInfoReq permissionInfo) throws Exception;
	/**
	 * 獲取所有一級菜單
	 */
	List<SysPermission> findParentPermission();
	/**
	 * 獲取權限基本信息
	 * @param permissionId
	 */
	SysPermission findPermissionById(int permissionId);

	/**
	 * 获取所有权限
	 */
	List<SysPermission> getAllPermission();
}
