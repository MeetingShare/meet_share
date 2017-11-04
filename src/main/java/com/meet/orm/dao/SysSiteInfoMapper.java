package com.meet.orm.dao;

import com.meet.orm.pojo.SysSiteInfo;

import java.util.List;

public interface SysSiteInfoMapper {
    int insert(SysSiteInfo record);
    int insertSelective(SysSiteInfo record);
    SysSiteInfo selectByPrimaryKey(Integer id);
    int updateByPrimaryKeySelective(SysSiteInfo record);
    int updateByPrimaryKey(SysSiteInfo record);
    List<SysSiteInfo> findSiteList(String type);
    int deleteSiteInfoById(Integer id);
}