package com.meet.orm.dao;

import java.util.List;

import com.meet.orm.pojo.SysUserRole;

public interface SysUserRoleMapper {

    int insertSelective(SysUserRole record);

    SysUserRole selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysUserRole record);


    int deleteUserRoleByUserId(int userId);
    
}