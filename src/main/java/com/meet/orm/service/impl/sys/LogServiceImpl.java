package com.meet.orm.service.impl.sys;

import com.meet.orm.dao.SysUserOperateLogMapper;
import com.meet.orm.pojo.SysUserOperateLog;
import com.meet.orm.service.sys.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by bzhx on 2017-11-01 23:12.
 */
@Service
public class LogServiceImpl implements LogService {

    @Autowired
    private SysUserOperateLogMapper logMapper;


    @Override
    public int saveLog(SysUserOperateLog log) {
        return logMapper.insertSelective(log);
    }
}