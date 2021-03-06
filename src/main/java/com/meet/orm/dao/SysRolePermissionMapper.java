package com.meet.orm.dao;

import java.util.List;

import com.meet.orm.pojo.SysRolePermission;

public interface SysRolePermissionMapper {

    int insertSelective(SysRolePermission record);

    SysRolePermission selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysRolePermission record);


    int deleteRoleAllPermissionByRoleId(int roleId);
}