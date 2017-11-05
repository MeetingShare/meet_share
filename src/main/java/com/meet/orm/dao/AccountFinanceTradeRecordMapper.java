package com.meet.orm.dao;

import com.meet.orm.pojo.AccountFinanceTradeRecord;

public interface AccountFinanceTradeRecordMapper {
    int insert(AccountFinanceTradeRecord record);

    int insertSelective(AccountFinanceTradeRecord record);

    AccountFinanceTradeRecord selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AccountFinanceTradeRecord record);

    int updateByPrimaryKey(AccountFinanceTradeRecord record);
}