package com.meet.orm.dao;

import com.meet.orm.pojo.AccountReservateOrder;

public interface AccountReservateOrderMapper {
    int insert(AccountReservateOrder record);

    int insertSelective(AccountReservateOrder record);

    AccountReservateOrder selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AccountReservateOrder record);

    int updateByPrimaryKey(AccountReservateOrder record);
}