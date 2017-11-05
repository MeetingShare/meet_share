package com.meet.orm.service;

import com.github.pagehelper.PageInfo;
import com.meet.dto.req.AccountInfoReq;
import com.meet.orm.pojo.AccountInfo;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by bzhx on 2017-11-05 22:34.
 */
public interface AccountService {
    /**
     * 发送验证吗
     *
     * @param reqInfo
     * @throws Exception
     */
     void sendCode(AccountInfoReq reqInfo) throws Exception;

    /**
     * 会员登陆
     *
     * @param reqInfo
     * @throws Exception
     */
    int login(AccountInfoReq reqInfo) throws Exception;

    /**
     * 会员注册
     *
     * @param reqInfo
     * @throws Exception
     */
    int register(AccountInfoReq reqInfo) throws Exception;

    /**
     * 获取会员基本信息
     *
     * @param accountId
     * @return
     */
     AccountInfo getAccountInfo(Integer accountId);

    /**
     * 更新用户信息
     *
     * @param reqInfo
     */
    void modifyAccountInfo(AccountInfoReq reqInfo) throws Exception;

    /**
     * 修改密码
     *
     * @param reqInfo
     * @throws Exception
     */
    void modifyPassword(AccountInfoReq reqInfo) throws Exception;

    /**
     * 找回密码
     * @param reqInfo
     * @throws Exception
     */
    void backPassword(AccountInfoReq reqInfo) throws Exception;

    /**
     * 获取会员列表分页
     * @param reqInfo
     * @return
     */
    PageInfo<AccountInfo> findAccountListPage(AccountInfoReq reqInfo);
}