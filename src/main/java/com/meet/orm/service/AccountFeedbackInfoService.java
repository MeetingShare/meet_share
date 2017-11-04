package com.meet.orm.service;

import com.github.pagehelper.PageInfo;
import com.meet.dto.req.AccountFeedbackInfoReq;
import com.meet.orm.pojo.AccountFeedbackInfo;

/**
 * Created by bzhx on 2017-11-04 13:58.
 */
public interface AccountFeedbackInfoService {
    /**
     * 获取主题列表基于分页
     * @param reqInfo
     * @return
     */
    PageInfo<AccountFeedbackInfo> findAccountFeedbackInfoListPage(AccountFeedbackInfoReq reqInfo);

    /**
     * 处理用户反馈的意见
     * @param reqInfo
     * @return
     */
    int updateAccountFeedbackInfo(AccountFeedbackInfoReq reqInfo);
}