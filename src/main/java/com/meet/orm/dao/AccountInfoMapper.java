package com.meet.orm.dao;

import com.meet.orm.pojo.AccountInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AccountInfoMapper {
    int insert(AccountInfo record);

    int insertSelective(AccountInfo record);

    AccountInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AccountInfo record);

    int updateByPrimaryKey(AccountInfo record);

    AccountInfo selectByAccount(String account);

    int deleteById(Integer userId);

    AccountInfo selectById(Integer id);

    List<AccountInfo> selectByPage(@Param("account")String account);

    List<AccountInfo> selectAll();

    AccountInfo selectByOpenId(String openId);

    int updatePass(AccountInfo record);
}