package com.meet.orm.dao;

import com.meet.orm.pojo.AccountReservateOrderDetail;

public interface AccountReservateOrderDetailMapper {
    int insert(AccountReservateOrderDetail record);

    int insertSelective(AccountReservateOrderDetail record);

    AccountReservateOrderDetail selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AccountReservateOrderDetail record);

    int updateByPrimaryKey(AccountReservateOrderDetail record);
}