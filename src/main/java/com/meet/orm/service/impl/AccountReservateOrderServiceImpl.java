package com.meet.orm.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.meet.common.constants.MeetConstants;
import com.meet.dto.req.AccountReservateOrderReq;
import com.meet.exception.SystemException;
import com.meet.orm.dao.AccountInfoMapper;
import com.meet.orm.dao.AccountReservateOrderMapper;
import com.meet.orm.dao.MeetingInfoMapper;
import com.meet.orm.pojo.AccountFeedbackInfo;
import com.meet.orm.pojo.AccountInfo;
import com.meet.orm.pojo.AccountReservateOrder;
import com.meet.orm.pojo.MeetingInfo;
import com.meet.orm.service.AccountReservateOrderService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by bzhx on 2017-11-07 20:19.
 */
@Service
public class AccountReservateOrderServiceImpl implements AccountReservateOrderService {

    @Autowired
    private AccountReservateOrderMapper accountReservateOrderMapper;
    @Autowired
    private AccountInfoMapper accountInfoMapper;
    @Autowired
    private MeetingInfoMapper meetingInfoMapper;
    @Override
    public PageInfo<AccountReservateOrder> findReservateOrderListPage(AccountReservateOrderReq reqInfo) {
        PageHelper.startPage(reqInfo.getPageCurrent(), reqInfo.getPageSize());
        AccountReservateOrder queryData=new AccountReservateOrder();
        BeanUtils.copyProperties(reqInfo,queryData);
        List<AccountReservateOrder> list = accountReservateOrderMapper.findAllReservateOrders(queryData);
        PageInfo<AccountReservateOrder> pageInfo = new PageInfo<AccountReservateOrder>(list);
        return pageInfo;
    }

    @Override
    public AccountReservateOrder findOrderInfo(Integer id) {
        return accountReservateOrderMapper.selectByPrimaryKey(id);
    }
    @Override
    public int reservate(AccountReservateOrderReq reqInfo) throws Exception {

         AccountReservateOrder data=accountReservateOrderMapper.selectByUID(reqInfo.getUid());
         if(data!=null){
             //判断用户是否有未支付订单
             if(MeetConstants.RESERVE_STATUS_NOT_PAY.equals(data.getStatus())){
                 throw new SystemException("你有未支付订单，请支付后在预定！");
             }
             //验证用户预定的会议室日期时间是否已经存在
             if(data.getMeetNo().equals(reqInfo.getMeetNo())&&data.getMeetDate().equals(reqInfo.getMeetDate())){

             }
         }
        return 0;
    }

    private AccountInfo validateAccountParam(AccountReservateOrderReq reqInfo) throws SystemException {
        if(reqInfo.getUid()==null){
            throw new SystemException("用户编号为空");
        }
        AccountInfo account=accountInfoMapper.selectById(reqInfo.getUid());
        if(account==null){
            throw new SystemException("当前用户不存在");
        }
        return account;
    }
    private MeetingInfo validateMeetParam(AccountReservateOrderReq reqInfo) throws SystemException {
        if(StringUtils.isEmpty(reqInfo.getMeetNo())){
            throw new SystemException("会议室编号为空");
        }
        MeetingInfo meet=meetingInfoMapper.selectByMeetNo(reqInfo.getMeetNo());
        if(meet==null){
            throw new SystemException("会议室编号["+reqInfo.getMeetNo()+"]对应的会议室不存在");
        }
        if("1".equals(meet.getStatus())){
            throw new SystemException("会议室编号["+reqInfo.getMeetNo()+"]对应的会议室不可用");
        }
        if(StringUtils.isEmpty(reqInfo.getMeetDate())){
            throw new SystemException("预定日期为空");
        }
        if(StringUtils.isEmpty(reqInfo.getMeetStartTime())){
            throw new SystemException("预定开始日期为空");
        }
        if(StringUtils.isEmpty(reqInfo.getMeetEndTime())){
            throw new SystemException("预定结束日期为空");
        }
        //验证预定日期是否在会议室规定的时间内
        if(Double.parseDouble(reqInfo.getMeetStartTime())<Double.parseDouble(meet.getStartTime())||
                Double.parseDouble(reqInfo.getMeetStartTime())>Double.parseDouble(meet.getEndTime())){
            throw new SystemException("预定开始时间不在会议室规则的时间段内");
        }
        if(Double.parseDouble(reqInfo.getMeetEndTime())<Double.parseDouble(meet.getEndTime())||
                Double.parseDouble(reqInfo.getMeetEndTime())>Double.parseDouble(meet.getEndTime())){
            throw new SystemException("预定结束时间不在会议室规则的时间段内");
        }
        //验证预定日期和预定的时间段是否已经被预定过了
        return meet;
    }
    private void validateReservateParam(AccountReservateOrderReq reqInfo)throws  SystemException{
        //验证当前日期是否有
    }
}