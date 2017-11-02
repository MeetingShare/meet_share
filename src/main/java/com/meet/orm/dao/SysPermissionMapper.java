package com.meet.orm.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.meet.orm.pojo.SysPermission;

public interface SysPermissionMapper {

	int insertSelective(SysPermission record);

	SysPermission selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(SysPermission record);


	List<SysPermission> selectAll();

	List<SysPermission> findPermissionListPage(@Param("name") String name);

	SysPermission selectByNameAndFId(@Param("name") String name,@Param("fId") int fId);

	int delete(Integer id);

	List<SysPermission>  selectParentPermission();
}