package com.meet.orm.service;

import com.github.pagehelper.PageInfo;
import com.meet.dto.req.AccountReservateOrderReq;
import com.meet.orm.pojo.AccountReservateOrder;

/**
 * Created by bzhx on 2017-11-07 20:18.
 */
public interface AccountReservateOrderService {
    /**
     * 获取预定列表
     * @param reqInfo
     * @return
     */
    PageInfo<AccountReservateOrder> findReservateOrderListPage(AccountReservateOrderReq reqInfo);

    /**
     * 获取订单详细信息
     * @param id
     * @return
     */
    AccountReservateOrder findOrderInfo(Integer id);

    /**
     * 用户预定会议室
     * @param reqInfo
     * @return
     * @throws Exception
     */
    int  reservate(AccountReservateOrderReq reqInfo) throws Exception;
}