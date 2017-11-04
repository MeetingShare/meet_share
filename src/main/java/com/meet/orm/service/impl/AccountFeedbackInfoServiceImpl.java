package com.meet.orm.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.meet.dto.req.AccountFeedbackInfoReq;
import com.meet.orm.dao.AccountFeedbackInfoMapper;
import com.meet.orm.pojo.AccountFeedbackInfo;
import com.meet.orm.service.AccountFeedbackInfoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by bzhx on 2017-11-04 14:05.
 */
@Service
public class AccountFeedbackInfoServiceImpl implements AccountFeedbackInfoService {

    @Autowired
    private AccountFeedbackInfoMapper accountFeedbackInfoMapper;

    @Override
    public PageInfo<AccountFeedbackInfo> findAccountFeedbackInfoListPage(AccountFeedbackInfoReq reqInfo) {
        PageHelper.startPage(reqInfo.getPageCurrent(),
                reqInfo.getPageSize());
        List<AccountFeedbackInfo> list = accountFeedbackInfoMapper.findAllAccountFeedbackInfo(reqInfo.getUid());
        PageInfo<AccountFeedbackInfo> pageInfo = new PageInfo<AccountFeedbackInfo>(list);
        return pageInfo;
    }

    @Override
    public int updateAccountFeedbackInfo(AccountFeedbackInfoReq reqInfo) {
        AccountFeedbackInfo info=new AccountFeedbackInfo();
        BeanUtils.copyProperties(reqInfo,info);
        info.setUpdateTime(new Date());
        info.setBackTime(new Date());
        info.setState("1");
        return accountFeedbackInfoMapper.updateByPrimaryKeySelective(info);
    }
}