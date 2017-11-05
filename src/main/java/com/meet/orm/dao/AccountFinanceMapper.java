package com.meet.orm.dao;

import com.meet.orm.pojo.AccountFinance;

public interface AccountFinanceMapper {
    int insert(AccountFinance record);

    int insertSelective(AccountFinance record);

    AccountFinance selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AccountFinance record);

    int updateByPrimaryKey(AccountFinance record);
}