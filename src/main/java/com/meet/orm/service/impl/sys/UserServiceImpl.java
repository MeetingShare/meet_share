package com.meet.orm.service.impl.sys;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.taglibs.standard.resources.Resources;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.meet.dto.req.sys.SysUserInfoReq;
import com.meet.exception.SystemException;
import com.meet.orm.dao.SysUserMapper;
import com.meet.orm.dao.SysUserRoleMapper;
import com.meet.orm.pojo.SysPermission;
import com.meet.orm.pojo.SysUser;
import com.meet.orm.pojo.SysUserRole;
import com.meet.orm.service.sys.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	SysUserMapper userDao;
	@Autowired
	SysUserRoleMapper userRoleMapper;

	@Override
	public List<SysPermission> findUserMenu(Integer userId) {
		// TODO Auto-generated method stub
		return userDao.findUserMenu(userId);
	}

	@Override
	public SysUser findUserByName(String name) {
		// TODO Auto-generated method stub
		return userDao.findUserByName(name);
	}

	@Override
	public PageInfo<SysUser> findUserListPage(SysUserInfoReq userInfo) {
		// TODO Auto-generated method stub
		PageHelper.startPage(userInfo.getPageCurrent(), userInfo.getPageSize());
		List<SysUser> list = userDao.findUserListPage(userInfo.getUsername());
		PageInfo<SysUser> pageInfo = new PageInfo<SysUser>(list);
		return pageInfo;
	}

	@Override
	public SysUser saveUser(SysUserInfoReq userInfo) throws Exception {
		// TODO Auto-generated method stub
		if (!userInfo.getPassword().equals(userInfo.getRepassword())) {
			throw new SystemException(Resources.getMessage("user_add_pass_error"));
		}
		if (userInfo.getRoleId() == null || userInfo.getRoleId().length <= 0) {
			throw new SystemException(Resources.getMessage("user_add_not_role"));
		}
		// 验证用户是否存在
		if (findUserByName(userInfo.getUsername()) != null) {
			throw new SystemException(Resources.getMessage("user_add_exit"));
		}
		// 保存用户信息
		SysUser user = new SysUser();
		BeanUtils.copyProperties(userInfo, user);
		userDao.insertSelective(user);
		// 保存用户角色
		for (int roleId : userInfo.getRoleId()) {
			SysUserRole userRole = new SysUserRole();
			userRole.setRoleId(roleId);
			userRole.setUserId(user.getId());
			userRoleMapper.insertSelective(userRole);
		}
		return user;
	}

	@Override
	public int delUser(int userId) throws Exception {
		// TODO Auto-generated method stub
		//先删除用户角色
		userRoleMapper.deleteUserRoleByUserId(userId);
		return userDao.delUserById(userId);
	}

	@Override
	public SysUser selectUserByUserId(int userId) {
		// TODO Auto-generated method stub
		return userDao.selectUserById(userId);
	}

	@Override
	public void updateUser(SysUserInfoReq userInfo) throws Exception {
		// TODO Auto-generated method stub
		if (StringUtils.isEmpty(userInfo.getUserId())) {
			throw new SystemException(Resources.getMessage("user_edit_fail"));
		}
		if (!userInfo.getPassword().equals(userInfo.getRepassword())) {
			throw new SystemException(Resources.getMessage("user_edit_pass_error"));
		}
		if (userInfo.getRoleId() == null || userInfo.getRoleId().length <= 0) {
			throw new SystemException(Resources.getMessage("user_edit_not_role"));
		}
		// 更新用户信息
		SysUser user = new SysUser();
		BeanUtils.copyProperties(userInfo, user);
		user.setId(Integer.parseInt(userInfo.getUserId()));
		userDao.updateByPrimaryKeySelective(user);
		// 先删除用户角色
		userRoleMapper.deleteUserRoleByUserId(user.getId());
		// 保存用户角色
		for (int roleId : userInfo.getRoleId()) {
			SysUserRole userRole = new SysUserRole();
			userRole.setRoleId(roleId);
			userRole.setUserId(user.getId());
			userRoleMapper.insertSelective(userRole);
		}
	}

	@Override
	public void editPass(SysUserInfoReq userInfo) throws Exception {
		// TODO Auto-generated method stub
		SysUser user = new SysUser();
		BeanUtils.copyProperties(userInfo, user);
		user.setId(Integer.parseInt(userInfo.getUserId()));
		userDao.updateByPrimaryKeySelective(user);
	}

}
