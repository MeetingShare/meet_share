package com.meet.orm.dao;

import java.util.List;

import com.meet.orm.pojo.SysUserRole;

public interface SysUserRoleMapper {
    int insert(SysUserRole record);

    int insertSelective(SysUserRole record);

    SysUserRole selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysUserRole record);

    int updateByPrimaryKey(SysUserRole record);
    
    int deleteUserRoleByUserId(int userId);
    
    List<SysUserRole> selectUserRoleByUserId(int userId);
}