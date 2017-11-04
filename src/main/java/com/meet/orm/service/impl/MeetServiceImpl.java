package com.meet.orm.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.meet.common.CommonUtils;
import com.meet.common.baidu.BaiduClient;
import com.meet.common.baidu.BaiduResponse;
import com.meet.common.baidu.RetLocation;
import com.meet.common.constants.MeetConstants;
import com.meet.dto.req.MeetingInfoReq;
import com.meet.exception.BusinessException;
import com.meet.exception.SystemException;
import com.meet.orm.dao.MeetImageMapper;
import com.meet.orm.dao.MeetingInfoMapper;
import com.meet.orm.pojo.MeetImage;
import com.meet.orm.pojo.MeetingInfo;
import com.meet.orm.service.MeetService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by bzhx on 2017-11-04 9:59.
 */
@Service
public class MeetServiceImpl implements MeetService {

    private Logger logger= LoggerFactory.getLogger(MeetConstants.LOG_NAME);

    @Autowired
    private MeetingInfoMapper meetingInfoMapper;
    @Autowired
    private MeetImageMapper meetImageMapper;

    @Override
    public PageInfo<MeetingInfo> findMeetListPage(MeetingInfoReq reqInfo) {
        PageHelper.startPage(reqInfo.getPageCurrent(),
                reqInfo.getPageSize());
        List<MeetingInfo> list = meetingInfoMapper.findAllMeet(reqInfo.getName());
        PageInfo<MeetingInfo> pageInfo = new PageInfo<MeetingInfo>(list);
        return pageInfo;
    }

    @Override
    public int addMeet(MeetingInfoReq reqInfo) throws Exception {
        //校验锁编号是否存在
        MeetingInfo meetData=meetingInfoMapper.selectMeetByLockNo(reqInfo.getLockNo());
        if(meetData!=null){
            throw new SystemException(reqInfo.getLockNo()+"已经被其他会议室锁定");
        }
        meetData=new MeetingInfo();
        BeanUtils.copyProperties(reqInfo,meetData);
        meetData.setCreateTime(new Date());
        //生成会议室编号
         meetData.setMeetNo(CommonUtils.getMeetNo());
        // 调用百度地图api获取坐标
        BaiduResponse response = BaiduClient.reqBaiduGetLanAndLgn(reqInfo.getAddress());
        if (response != null && response.getStatus() == 0) {
            RetLocation location = response.getResult().getLocation();
            meetData.setLat(location.getLat());
            meetData.setLng(location.getLng());
        }
       int flag= meetingInfoMapper.insertSelective(meetData);
        saveGoodImage(meetData.getId(),reqInfo.getImageIds(),reqInfo.getImageDefault());
      return flag;
    }

    @Override
    public int updateMeet(MeetingInfoReq reqInfo) throws Exception {
        //校验锁编号是否存在
        MeetingInfo meetData=meetingInfoMapper.selectMeetByLockNo(reqInfo.getLockNo());
        if(meetData!=null&&meetData.getId()!=reqInfo.getId()){
            throw new SystemException(reqInfo.getLockNo()+"已经被其他会议室锁定");
        }
        meetData=new MeetingInfo();
        BeanUtils.copyProperties(reqInfo,meetData);
        meetData.setUpdateTime(new Date());
        // 调用百度地图api获取坐标
        BaiduResponse response = BaiduClient.reqBaiduGetLanAndLgn(reqInfo.getAddress());
        if (response != null && response.getStatus() == 0) {
            RetLocation location = response.getResult().getLocation();
            meetData.setLat(location.getLat());
            meetData.setLng(location.getLng());
        }
        int flag=meetingInfoMapper.updateByPrimaryKeySelective(meetData);
        saveGoodImage(meetData.getId(),reqInfo.getImageIds(),reqInfo.getImageDefault());
        return flag;
    }

    @Override
    public int delMeet(Integer id) throws Exception {
        return meetingInfoMapper.deleteMeetById(id);
    }

    @Override
    public MeetingInfo getMeetById(Integer id) {
        return meetingInfoMapper.selectInfoById(id);
    }

    private void saveGoodImage(Integer meetId, String imageIds, String imageDefault) throws Exception {
        if(StringUtils.isEmpty(imageIds)){
            return;
        }
        try {
            String[] imagesId = imageIds.split(",");
            String defaultImageId = "0";
            if (StringUtils.isNotEmpty(imageDefault) && imageDefault.indexOf("-") != -1) {
                String[] defaults = imageDefault.split("-");
                defaultImageId = (defaults[0]);
            }
            // 删除所有图片对应关系
            meetImageMapper.deleteByMeetId(meetId);
            for (String imageId : imagesId) {
                MeetImage pvi = new MeetImage();
                pvi.setMeetId(meetId);
                pvi.setImageId(Integer.valueOf(imageId));
                if (defaultImageId.equals(imageId)) {
                    pvi.setIsDefault(MeetConstants.IS_DEFAULT);
                }
                meetImageMapper.insertSelective(pvi);
            }
        } catch (Exception e) {
            logger.error("保存门店图片异常：{}", e.getMessage());
            throw new BusinessException(e.getMessage());
        }
    }
}