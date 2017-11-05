package com.meet.orm.dao;

import com.google.common.base.Strings;
import com.meet.orm.pojo.MeetPriceRules;

import java.util.List;

public interface MeetPriceRulesMapper {
    int insert(MeetPriceRules record);

    int insertSelective(MeetPriceRules record);

    MeetPriceRules selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MeetPriceRules record);

    int updateByPrimaryKey(MeetPriceRules record);

    List<MeetPriceRules> findAllMeetPriceRules(MeetPriceRules info);

    int deleteById(Integer id);

    MeetPriceRules selectRuleIsExits(MeetPriceRules info);
}