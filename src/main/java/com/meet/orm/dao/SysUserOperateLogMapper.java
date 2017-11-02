package com.meet.orm.dao;

import com.meet.orm.pojo.SysUserOperateLog;

public interface SysUserOperateLogMapper {

    int insertSelective(SysUserOperateLog record);

    SysUserOperateLog selectByPrimaryKey(Integer id);

}