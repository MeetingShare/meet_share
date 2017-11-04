package com.meet.orm.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.meet.dto.req.SysRoleInfoReq;
import com.meet.exception.SystemException;
import com.meet.orm.dao.SysPermissionMapper;
import com.meet.orm.dao.SysRoleMapper;
import com.meet.orm.dao.SysRolePermissionMapper;
import com.meet.orm.dao.SysUserRoleMapper;
import com.meet.orm.pojo.SysRole;
import com.meet.orm.pojo.SysRolePermission;
import com.meet.orm.service.RoleService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	SysRoleMapper roleMapper;
	@Autowired
	SysUserRoleMapper userRoleMapper;
	@Autowired
	SysPermissionMapper permissMapper;
	@Autowired
	SysRolePermissionMapper rolePermissMapper;

	@Override
	public List<SysRole> getAllRole() {
		// TODO Auto-generated method stub
		return roleMapper.getAllRole();
	}

	@Override
	public PageInfo<SysRole> findRoleListPage(SysRoleInfoReq roleInfo) {
		// TODO Auto-generated method stub
		PageHelper.startPage(roleInfo.getPageCurrent(), roleInfo.getPageSize());
		List<SysRole> list = roleMapper.findRoleListPage(roleInfo.getName());
		PageInfo<SysRole> pageInfo = new PageInfo<SysRole>(list);
		return pageInfo;
	}

	@Override
	public void addRole(SysRoleInfoReq roleInfo) throws Exception {
		// TODO Auto-generated method stub
		// 验证名称是否为空
		if (StringUtils.isEmpty(roleInfo.getName())) {
			throw new SystemException("角色名称为空");
		}
		// 验证是否有重复的角色名称a
		SysRole role = roleMapper.selectByName(roleInfo.getName());
		if (role != null) {
			throw new SystemException("角色已经存在");
		}
		role = new SysRole();
		BeanUtils.copyProperties(roleInfo, role);
		roleMapper.insertSelective(role);
	}

	@Override
	public int delRole(int roleId) throws Exception {
		// TODO Auto-generated method stub
		return roleMapper.delete(roleId);
	}
	@Override
	public void addRolePermission(SysRoleInfoReq roleInfo) throws Exception {
		// TODO Auto-generated method stub
		if (StringUtils.isEmpty(roleInfo.getRoleId())) {
			throw new SystemException("角色ID为空");
		}
			if (StringUtils.isEmpty(roleInfo.getModuleIds())) {
			throw new SystemException("权限为空");
		}
		// 删除角色对应的所有权限
		rolePermissMapper.deleteRoleAllPermissionByRoleId(Integer
				.parseInt(roleInfo.getRoleId()));
		String[] permissionIds = roleInfo.getModuleIds().split(",");
		for (String permissionId : permissionIds) {
			SysRolePermission record = new SysRolePermission();
			record.setPermissionId(Integer.parseInt(permissionId));
			record.setRoleId(Integer.parseInt(roleInfo.getRoleId()));
			rolePermissMapper.insertSelective(record);
		}

	}

	@Override
	public SysRole selectByRoleId(Integer roleId) {
		return roleMapper.selectById(roleId);
	}

	@Override
	public int updateRole(SysRoleInfoReq roleInfo) throws Exception {
		// 验证名称是否为空
		if (StringUtils.isEmpty(roleInfo.getName())) {
			throw new SystemException("角色名称为空");
		}
		if (StringUtils.isEmpty(roleInfo.getRoleId())) {
			throw new SystemException("角色ID为空");
		}
		// 验证是否有重复的角色名称a
		SysRole role = roleMapper.selectByName(roleInfo.getName());
		if (role != null) {
			throw new SystemException("角色已经存在");
		}
		role = new SysRole();
		role.setId(Integer.parseInt(roleInfo.getRoleId()));
		BeanUtils.copyProperties(roleInfo, role);
		return roleMapper.updateByPrimaryKeySelective(role);
	}
}
