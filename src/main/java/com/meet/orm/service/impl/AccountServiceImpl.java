package com.meet.orm.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.meet.common.IdentifyCode;
import com.meet.common.Md5Util;
import com.meet.common.cache.CacheUtil;
import com.meet.common.constants.MeetConstants;
import com.meet.common.date.DateUtil;
import com.meet.common.sms.SMUtil;
import com.meet.dto.req.AccountInfoReq;
import com.meet.exception.BusinessException;
import com.meet.orm.dao.AccountInfoMapper;
import com.meet.orm.dao.AccountSendCodeMapper;
import com.meet.orm.pojo.AccountInfo;
import com.meet.orm.pojo.AccountSendCode;
import com.meet.orm.service.AccountService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Created by bzhx on 2017-11-05 22:35.
 */
@Service
public class AccountServiceImpl implements AccountService {

    private Logger logger = LoggerFactory.getLogger(MeetConstants.LOG_NAME);
    @Autowired
    private CacheUtil cacheUtil;
    @Autowired
    private AccountSendCodeMapper sendCodeMapper;
    @Autowired
    private AccountInfoMapper  accountInfoMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void sendCode(AccountInfoReq reqInfo) throws Exception {
            AccountSendCode data = sendCodeMapper.selectByAccount(reqInfo.getAccount());
            // 如果表中不存在数据则添加
            String sendCode="";
            if (data == null) {
                sendCode = IdentifyCode.getCode(6);
                data = new AccountSendCode();
                data.setCode(sendCode);
                data.setPhone(reqInfo.getAccount());
                data.setEditorTime(new Date());
                sendCodeMapper.insertSelective(data);
            } else {
                Date captchatime = data.getEditorTime();
                int bettonTime = DateUtil.minutesBetweenData(captchatime, new Date());
                // 大于15分钟的走重新发送
                if (bettonTime >= MeetConstants.SEND_CODE_TIME) {
                    sendCode = IdentifyCode.getCode(6);
                    data.setCode(sendCode);
                    data.setPhone(reqInfo.getAccount());
                    data.setEditorTime(new Date());
                    sendCodeMapper.updateByPrimaryKeySelective(data);
                } else {
                    sendCode = data.getCode();
                }
            }
        cacheUtil.put(MeetConstants.CACHE_SEND_CODE, reqInfo.getAccount(), sendCode);
        logger.info("短信验证码：{} 手机号：{}", sendCode,reqInfo.getAccount());
        // 调用短信客户端
        SMUtil.sendBatchSM(reqInfo.getAccount(), sendCode);
    }

    @Override
    public int login(AccountInfoReq reqInfo) throws Exception {
        // TODO Auto-generated method stub
        AccountInfo data = accountInfoMapper.selectByAccount(reqInfo.getAccount());
        if (data == null) {
            throw new BusinessException("手机号不存在");
        }
        // 判断用户状态
        if (MeetConstants.MEMBER_STATUS_JINYONG.equals(data.getStatus())) {
            throw new BusinessException("手机号已禁用");
        }
        // 账号密码登陆
        if (!data.getPassword().equals(Md5Util.md5Encode(reqInfo.getPassword()))) {
            throw new BusinessException("密码错误");
        }
        return data.getId();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int register(AccountInfoReq reqInfo) throws Exception {
        // TODO Auto-generated method stub
        // 验证手机号是否存在
        AccountInfo data = accountInfoMapper.selectByAccount(reqInfo.getAccount());
        if (data != null) {
            throw new BusinessException("手机号已经存在");
        }
        // 判断验证码
        String sendCode = cacheUtil.get(MeetConstants.CACHE_SEND_CODE, reqInfo.getAccount());
        if (StringUtils.isEmpty(sendCode)) {
            throw new BusinessException("验证码已过期");
        } else {
            // 比对验证码
            if (!sendCode.equals(reqInfo.getCode())) {
                throw new BusinessException("验证码有误");
            }
        }
        if (!reqInfo.getConfirmPass().equals(reqInfo.getPassword())) {
            throw new BusinessException("确认密码与密码不一致");
        }
        data = new AccountInfo();
        BeanUtils.copyProperties(reqInfo, data);
        data.setPassword(Md5Util.md5Encode(reqInfo.getPassword()));
        if (data.getId() != null) {
            accountInfoMapper.updateByPrimaryKeySelective(data);
        } else {
            accountInfoMapper.insertSelective(data);
        }
        return data.getId();
    }

    @Override
    public AccountInfo getAccountInfo(Integer AccountInfoId) {
        // TODO Auto-generated method stub
        return accountInfoMapper.selectById(AccountInfoId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void modifyAccountInfo(AccountInfoReq reqInfo) throws Exception {
        // TODO Auto-
        AccountInfo data = new AccountInfo();
        BeanUtils.copyProperties(reqInfo, data);
        accountInfoMapper.updateByPrimaryKeySelective(data);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void modifyPassword(AccountInfoReq reqInfo) throws Exception {
        // TODO Auto-generated method stub
        AccountInfo data = accountInfoMapper.selectByPrimaryKey(reqInfo.getId());
        if (data == null) {
            throw new BusinessException("手机号不存在");
        }
        if (!data.getPassword().equals(reqInfo.getOriginalPass())) {
            throw new BusinessException("原始密码有误");
        }
        if (!reqInfo.getConfirmPass().equals(reqInfo.getNewPass())) {
            throw new BusinessException("新密码与确认密码不一致");
        }
        data.setPassword(Md5Util.md5Encode(reqInfo.getConfirmPass()));
        accountInfoMapper.updateByPrimaryKeySelective(data);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void backPassword(AccountInfoReq reqInfo) throws Exception {
        // TODO Auto-generated method stub
        AccountInfo data = accountInfoMapper.selectByAccount(reqInfo.getAccount());
        if (data == null) {
            throw new BusinessException("手机号不存在");
        }
        // 判断验证码
        String sendCode = cacheUtil.get("sendCode", reqInfo.getAccount());
        if (StringUtils.isEmpty(sendCode)) {
            throw new BusinessException("验证码已经过期");
        } else {
            // 比对验证码
            if (!sendCode.equals(reqInfo.getCode())) {
                throw new BusinessException("验证码有误");
            }
        }
        if (!reqInfo.getConfirmPass().equals(reqInfo.getNewPass())) {
            throw new BusinessException("新密码与确认密码不一致");
        }
        data.setPassword(Md5Util.md5Encode(reqInfo.getConfirmPass()));
        accountInfoMapper.updateByPrimaryKeySelective(data);
    }

    @Override
    public PageInfo<AccountInfo> findAccountListPage(AccountInfoReq reqInfo) {
        PageHelper.startPage(reqInfo.getPageCurrent(),reqInfo.getPageSize());
        List<AccountInfo> list = accountInfoMapper.selectByPage(reqInfo.getAccount());
        PageInfo<AccountInfo> pageInfo = new PageInfo<AccountInfo>(list);
        return pageInfo;
    }
}