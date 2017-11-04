package com.meet.orm.dao;

import com.meet.orm.pojo.MeetSupportFacilities;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MeetSupportFacilitiesMapper {
    int insert(MeetSupportFacilities record);

    int insertSelective(MeetSupportFacilities record);

    MeetSupportFacilities selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MeetSupportFacilities record);

    int updateByPrimaryKey(MeetSupportFacilities record);

    List<MeetSupportFacilities> findAllMeetSupportFacilities(@Param("name") String name);

    int deleteData(Integer id);
}