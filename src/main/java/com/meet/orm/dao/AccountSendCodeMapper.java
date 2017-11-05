package com.meet.orm.dao;

import com.meet.orm.pojo.AccountSendCode;

public interface AccountSendCodeMapper {
    int insert(AccountSendCode record);

    int insertSelective(AccountSendCode record);

    AccountSendCode selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AccountSendCode record);

    int updateByPrimaryKey(AccountSendCode record);

    AccountSendCode selectByAccount(String phone);
}