package com.meet.orm.dao;

import com.meet.orm.pojo.MeetingTimeInfo;

public interface MeetingTimeInfoMapper {
    int insert(MeetingTimeInfo record);

    int insertSelective(MeetingTimeInfo record);

    MeetingTimeInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MeetingTimeInfo record);

    int updateByPrimaryKey(MeetingTimeInfo record);
}