package com.meet.orm.dao;

import com.meet.orm.pojo.AccountReservateOrder;
import org.apache.shiro.authc.Account;

import java.util.List;

public interface AccountReservateOrderMapper {
    int insert(AccountReservateOrder record);

    int insertSelective(AccountReservateOrder record);

    AccountReservateOrder selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AccountReservateOrder record);

    int updateByPrimaryKey(AccountReservateOrder record);

    List<AccountReservateOrder> findAllReservateOrders(AccountReservateOrder queryInfo);

    AccountReservateOrder selectByUID(Integer uid);
}