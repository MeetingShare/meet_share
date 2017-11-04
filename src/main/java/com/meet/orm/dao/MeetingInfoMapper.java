package com.meet.orm.dao;

import com.meet.orm.pojo.MeetingInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MeetingInfoMapper {

    int insertSelective(MeetingInfo record);

    MeetingInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MeetingInfo record);


    List<MeetingInfo> findAllMeet(@Param("name")String name);

    MeetingInfo selectMeetByLockNo(String lockNo);

    int deleteMeetById(Integer id);

    MeetingInfo selectInfoById(Integer id);

}