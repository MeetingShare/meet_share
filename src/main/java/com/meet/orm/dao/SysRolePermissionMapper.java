package com.meet.orm.dao;

import java.util.List;

import com.meet.orm.pojo.SysRolePermission;

public interface SysRolePermissionMapper {
    int insert(SysRolePermission record);

    int insertSelective(SysRolePermission record);

    SysRolePermission selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysRolePermission record);

    int updateByPrimaryKey(SysRolePermission record);
    
    List<SysRolePermission> selectRolePermissionByRoleId(int roleId);
    
    int deleteRoleAllPermissionByRoleId(int roleId);
}