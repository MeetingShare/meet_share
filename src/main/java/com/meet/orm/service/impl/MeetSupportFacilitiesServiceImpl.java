package com.meet.orm.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.meet.dto.req.MeetSupportFacilitiesReq;
import com.meet.orm.dao.MeetSupportFacilitiesMapper;
import com.meet.orm.pojo.MeetSupportFacilities;
import com.meet.orm.service.MeetSupportFacilitiesService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by bzhx on 2017-11-04 9:59.
 */
@Service
public class MeetSupportFacilitiesServiceImpl implements MeetSupportFacilitiesService {


    @Autowired
    private MeetSupportFacilitiesMapper meetSupportFacilitiesMapper;

    @Override
    public PageInfo<MeetSupportFacilities> findMeetSupportFacilitiesListPage(MeetSupportFacilitiesReq reqInfo) {
        PageHelper.startPage(reqInfo.getPageCurrent(),
                reqInfo.getPageSize());
        List<MeetSupportFacilities> list = meetSupportFacilitiesMapper.findAllMeetSupportFacilities(reqInfo.getName());
        PageInfo<MeetSupportFacilities> pageInfo = new PageInfo<MeetSupportFacilities>(list);
        return pageInfo;
    }

    @Override
    public List<MeetSupportFacilities> findMeetSupportFacilitiesList(MeetSupportFacilitiesReq reqInfo) {
        return meetSupportFacilitiesMapper.findAllMeetSupportFacilities(reqInfo.getName());
    }

    @Override
    public int saveData(MeetSupportFacilitiesReq reqInfo) throws Exception {
        MeetSupportFacilities data=new MeetSupportFacilities();
        BeanUtils.copyProperties(reqInfo,data);
        data.setCreateTime(new Date());
        return meetSupportFacilitiesMapper.insertSelective(data);
    }

    @Override
    public int delData(int id) throws Exception {
        return meetSupportFacilitiesMapper.deleteData(id);
    }

    @Override
    public void updateData(MeetSupportFacilitiesReq reqInfo) throws Exception {
        MeetSupportFacilities data=new MeetSupportFacilities();
        BeanUtils.copyProperties(reqInfo,data);
        data.setUpdateTime(new Date());
        meetSupportFacilitiesMapper.updateByPrimaryKeySelective(data);
    }

    @Override
    public MeetSupportFacilities selectInfoById(int id) {
        return meetSupportFacilitiesMapper.selectByPrimaryKey(id);
    }
}