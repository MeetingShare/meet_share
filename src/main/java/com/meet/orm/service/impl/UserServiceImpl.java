package com.meet.orm.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.meet.common.Md5Util;
import com.meet.dto.req.SysUserInfoReq;
import com.meet.exception.SystemException;
import com.meet.orm.dao.SysUserMapper;
import com.meet.orm.dao.SysUserRoleMapper;
import com.meet.orm.pojo.SysPermission;
import com.meet.orm.pojo.SysUser;
import com.meet.orm.pojo.SysUserRole;
import com.meet.orm.service.UserService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
		// 验证用户是否存在
		if (findUserByName(userInfo.getUsername()) != null) {
			throw new SystemException("用户已存在！");
		}
		// 保存用户信息
		SysUser user = new SysUser();
		user.setUsername(userInfo.getUsername());
		user.setPassword(Md5Util.md5Encode(userInfo.getPassword()));
		userDao.insertSelective(user);
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
			throw new SystemException("用户ID为空！");
		}
		// 更新用户信息
		SysUser user = new SysUser();
		user.setId(Integer.parseInt(userInfo.getUserId()));
		if(StringUtils.isNotEmpty(userInfo.getPassword())) {
			user.setPassword(Md5Util.md5Encode(userInfo.getPassword()));
		}
		if(StringUtils.isNotEmpty(userInfo.getActive())){
			user.setActive(userInfo.getActive());
		}
		userDao.updateByPrimaryKeySelective(user);
	}

	@Override
	public void editPass(SysUserInfoReq userInfo) throws Exception {
		// TODO Auto-generated method stub
		SysUser user = new SysUser();
		BeanUtils.copyProperties(userInfo, user);
		user.setId(Integer.parseInt(userInfo.getUserId()));
		userDao.updateByPrimaryKeySelective(user);
	}

	@Override
	public void setRole(SysUserInfoReq userInfo) throws Exception {
		if (StringUtils.isEmpty(userInfo.getUserId())) {
			throw new SystemException("用户ID为空！");
		}
		SysUser user=selectUserByUserId(Integer.parseInt(userInfo.getUserId()));
		if(user==null){
			throw new SystemException("用户不存在");
		}
		try {
			if (userInfo.getRoleIds() == null && userInfo.getRoleIds().length <= 0) {
				throw new SystemException("角色不能为空");
			}
		}catch (NullPointerException e){
			throw new SystemException("角色不能为空");
		}
		// 先删除用户角色
		userRoleMapper.deleteUserRoleByUserId(user.getId());
		// 保存用户角色
		for (String roleId : userInfo.getRoleIds()) {
			SysUserRole userRole = new SysUserRole();
			userRole.setRoleId(Integer.parseInt(roleId));
			userRole.setUserId(user.getId());
			userRoleMapper.insertSelective(userRole);
		}
	}

	@Override
	public SysUser login(SysUserInfoReq userInfoReq) throws Exception {
		if(userInfoReq==null){
			throw new SystemException("用户名或密码为空");
		}
		if(StringUtils.isEmpty(userInfoReq.getUsername())){
			throw new SystemException("用户名为空");
		}
		if(StringUtils.isEmpty(userInfoReq.getPassword())){
			throw new SystemException("密码为空");
		}
		SysUser data=userDao.findUserByName(userInfoReq.getUsername());
		if(data==null){
			throw new SystemException("用户不存在");
		}
		if(!Md5Util.md5Encode(userInfoReq.getPassword()).equals(data.getPassword())){
			throw new SystemException("密码有误！");
		}
		if(!"0".equals(data.getActive())){
			throw new SystemException("用户已禁用");
		}

		return data;
	}
}
