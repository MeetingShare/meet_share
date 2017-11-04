package com.meet.orm.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.meet.dto.req.SiteInfoReq;
import com.meet.orm.dao.SysSiteInfoMapper;
import com.meet.orm.pojo.SysRole;
import com.meet.orm.pojo.SysSiteInfo;
import com.meet.orm.service.SiteInfoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * Created by bzhx on 2017-11-04 14:50.
 */
@Service
public class SiteInfoServiceImpl implements SiteInfoService{

    @Autowired
    private SysSiteInfoMapper siteInfoMapper;

    @Override
    public PageInfo<SysSiteInfo> findSiteInfoListPage(SiteInfoReq reqInfo) {
        PageHelper.startPage(reqInfo.getPageCurrent(), reqInfo.getPageSize());
        List<SysSiteInfo> list = siteInfoMapper.findSiteList(reqInfo.getType());
        PageInfo<SysSiteInfo> pageInfo = new PageInfo<SysSiteInfo>(list);
        return pageInfo;
    }

    @Override
    public SysSiteInfo findSiteInfoByType(String type) {
        List<SysSiteInfo> list=siteInfoMapper.findSiteList(type);
        return (list!=null && list.size()>0)?null:list.get(0);
    }

    @Override
    public int saveSiteInfo(SiteInfoReq reqInfo) throws Exception {
        SysSiteInfo data=new SysSiteInfo();
        BeanUtils.copyProperties(reqInfo,data);
        data.setCreateTime(new Date());
        return siteInfoMapper.insertSelective(data);
    }

    @Override
    public int delSiteInfo(int id) throws Exception {
        return siteInfoMapper.deleteSiteInfoById(id);
    }

    @Override
    public void updateSiteInfo(SiteInfoReq reqInfo) throws Exception {
        SysSiteInfo data=new SysSiteInfo();
        BeanUtils.copyProperties(reqInfo,data);
        siteInfoMapper.updateByPrimaryKeySelective(data);
    }

    @Override
    public SysSiteInfo selectSiteInfoById(int id) {
        return siteInfoMapper.selectByPrimaryKey(id);
    }
}