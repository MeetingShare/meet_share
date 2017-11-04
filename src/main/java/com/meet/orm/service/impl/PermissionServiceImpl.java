package com.meet.orm.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.meet.dto.req.SysPermissionInfoReq;
import com.meet.exception.SystemException;
import com.meet.orm.dao.SysPermissionMapper;
import com.meet.orm.pojo.SysPermission;
import com.meet.orm.service.PermissionService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 权限实现 Created by bzhx on 2017年3月17日 下午3:45:00
 */
@Service
public class PermissionServiceImpl implements PermissionService {

	@Autowired
	SysPermissionMapper permissionMapper;

	@Override
	public PageInfo<SysPermission> findPermissionListPage(
			SysPermissionInfoReq permissionReq) {
		PageHelper.startPage(permissionReq.getPageCurrent(),
				permissionReq.getPageSize());
		List<SysPermission> list = permissionMapper
				.findPermissionListPage(permissionReq.getName());
		PageInfo<SysPermission> pageInfo = new PageInfo<SysPermission>(list);
		return pageInfo;
	}

	@Override
	public void addPermission(SysPermissionInfoReq permission) throws Exception {
		// TODO Auto-generated method stub
		// 验证name和地址
		if (StringUtils.isEmpty(permission.getName())) {
			throw new SystemException("权限名称为空");
		}
		if (StringUtils.isEmpty(permission.getControllerUrl())) {
			throw new SystemException("权限地址为空");
		}
		// 验证同一父类是否包含该权限
		SysPermission record = permissionMapper.selectByNameAndFId(
				permission.getName(), permission.getFId());
		if (record != null) {
			throw new SystemException("权限已经存在");
		}
		record = new SysPermission();
		BeanUtils.copyProperties(permission, record);
		permissionMapper.insertSelective(record);
	}

	@Override
	public void updatePermission(SysPermissionInfoReq permissionInfo)
			throws Exception {
		// TODO Auto-generated method stub
		// 验证name和地址
		if (StringUtils.isEmpty(permissionInfo.getName())) {
			throw new SystemException("权限名称为空");
		}
		if (StringUtils.isEmpty(permissionInfo.getControllerUrl())) {
			throw new SystemException("权限地址为空");
		}
		SysPermission permission = new SysPermission();
		BeanUtils.copyProperties(permissionInfo, permission);
		permissionMapper.updateByPrimaryKeySelective(permission);
	}

	@Override
	public List<SysPermission> findParentPermission() {
		// TODO Auto-generated method stub
		return permissionMapper.selectParentPermission();
	}

	@Override
	public SysPermission findPermissionById(int permissionId) {
		// TODO Auto-generated method stub
		return permissionMapper.selectByPrimaryKey(permissionId);
	}

	@Override
	public void delPermission(int permissionId) throws Exception {
		// TODO Auto-generated method stub
		permissionMapper.delete(permissionId);
	}

	@Override
	public List<SysPermission> getAllPermission() {
		return permissionMapper.selectAll();
	}
}
