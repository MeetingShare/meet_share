package com.meet.orm.dao;

import com.meet.orm.pojo.MeetImage;

public interface MeetImageMapper {
    int insert(MeetImage record);

    int insertSelective(MeetImage record);

    MeetImage selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MeetImage record);

    int updateByPrimaryKey(MeetImage record);

    int deleteByMeetId(Integer storeId);

    MeetImage selectByImageId(Integer imageId);

    int deleteByImageId(Integer imageId);
}