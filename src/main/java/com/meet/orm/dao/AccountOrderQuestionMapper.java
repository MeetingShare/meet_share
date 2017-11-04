package com.meet.orm.dao;

import com.meet.orm.pojo.AccountOrderQuestion;

public interface AccountOrderQuestionMapper {
    int insert(AccountOrderQuestion record);

    int insertSelective(AccountOrderQuestion record);

    AccountOrderQuestion selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AccountOrderQuestion record);

    int updateByPrimaryKey(AccountOrderQuestion record);
}