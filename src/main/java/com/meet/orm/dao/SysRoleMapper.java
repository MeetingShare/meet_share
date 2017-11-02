package com.meet.orm.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.meet.orm.pojo.SysRole;

public interface SysRoleMapper {

    int insertSelective(SysRole record);

    SysRole selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysRole record);


	List<SysRole> getAllRole();
	
	List<SysRole> findRoleListPage(@Param("name")String name);
	
	SysRole selectByName(@Param("name")String name);
	
	int delete(Integer id);

    SysRole selectById(Integer id);
}