package com.meet.orm.dao;

import com.meet.orm.pojo.SysUserOperateLog;

public interface SysUserOperateLogMapper {
    int insert(SysUserOperateLog record);

    int insertSelective(SysUserOperateLog record);

    SysUserOperateLog selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysUserOperateLog record);

    int updateByPrimaryKeyWithBLOBs(SysUserOperateLog record);

    int updateByPrimaryKey(SysUserOperateLog record);
}