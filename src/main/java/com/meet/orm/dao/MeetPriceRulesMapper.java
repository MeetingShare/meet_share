package com.meet.orm.dao;

import com.meet.orm.pojo.MeetPriceRules;

public interface MeetPriceRulesMapper {
    int insert(MeetPriceRules record);

    int insertSelective(MeetPriceRules record);

    MeetPriceRules selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MeetPriceRules record);

    int updateByPrimaryKey(MeetPriceRules record);
}