package com.meet.orm.service.sys;

import com.meet.orm.pojo.SysUserOperateLog;

/**
 * Created by bzhx on 2017-11-01 23:11.
 */
public interface LogService {
    /**
     * 添加操作日志
     * @param log
     * @return
     */
    int saveLog(SysUserOperateLog log);
}