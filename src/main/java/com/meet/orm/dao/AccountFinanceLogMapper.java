package com.meet.orm.dao;

import com.meet.orm.pojo.AccountFinanceLog;

public interface AccountFinanceLogMapper {
    int insert(AccountFinanceLog record);

    int insertSelective(AccountFinanceLog record);

    AccountFinanceLog selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AccountFinanceLog record);

    int updateByPrimaryKey(AccountFinanceLog record);
}