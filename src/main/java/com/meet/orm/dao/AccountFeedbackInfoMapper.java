package com.meet.orm.dao;

import com.meet.orm.pojo.AccountFeedbackInfo;

import java.util.List;

public interface AccountFeedbackInfoMapper {
    int insert(AccountFeedbackInfo record);

    int insertSelective(AccountFeedbackInfo record);

    AccountFeedbackInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AccountFeedbackInfo record);

    int updateByPrimaryKey(AccountFeedbackInfo record);

    List<AccountFeedbackInfo> findAllAccountFeedbackInfo(Integer uid);
}