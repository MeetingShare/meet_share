package com.meet.orm.service.impl.sys;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.meet.common.resources.Resources;
import com.meet.dto.req.sys.SysPermissionInfoReq;
import com.meet.exception.SystemException;
import com.meet.orm.dao.SysPermissionMapper;
import com.meet.orm.pojo.SysPermission;
import com.meet.orm.service.sys.PermissionService;

/**
 * 权限实现 Created by bzhx on 2017年3月17日 下午3:45:00
 */
@Component
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
			throw new SystemException(Resources.getMessage("permiss_fail_name"));
		}
		if (StringUtils.isEmpty(permission.getControllerUrl())) {
			throw new SystemException(Resources.getMessage("permiss_fail_url"));
		}
		// 验证同一父类是否包含该权限
		SysPermission record = permissionMapper.selectByNameAndFId(
				permission.getName(), permission.getFId());
		if (record != null) {
			throw new SystemException(
					Resources.getMessage("permiss_fail_exits"));
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
			throw new SystemException(
					Resources.getMessage("permiss_ufail_name"));
		}
		if (StringUtils.isEmpty(permissionInfo.getControllerUrl())) {
			throw new SystemException(Resources.getMessage("permiss_ufail_url"));
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

}
