package com.meet.orm.service;

import com.github.pagehelper.PageInfo;
import com.meet.dto.req.MeetRulePriceReq;
import com.meet.orm.pojo.MeetPriceRules;

import java.util.List;

/**
 * Created by bzhx on 2017-11-05 20:06.
 */
public interface MeetPriceRulesService {
    /**
     * 获取会议室计价规则列表
     * @param reqInfo
     * @return
     */
    PageInfo<MeetPriceRules> findMeetPriceRuleListPage(MeetRulePriceReq reqInfo);

    /**
     * 添加会议室计价规则
     * @param reqInfo
     * @return
     * @throws Exception
     */
    int addMeetPriceRule(MeetRulePriceReq reqInfo) throws Exception;
    /**
     * 更新会议室计价规则
     * @param reqInfo
     * @return
     * @throws Exception
     */
    int updateMeetPriceRule(MeetRulePriceReq reqInfo) throws Exception;

    /**
     * 删除会议室计价规则，物理删除
     * @param id
     * @return
     * @throws Exception
     */
    int delMeetPriceRule(Integer id) throws Exception;

    /**
     * 获取某个时间规则详情
     * @param id
     * @return
     */
    MeetPriceRules getMeetPriceRuleById(Integer id);

    /**
     * 获取某个会议室详情
     * @param meetNo
     * @return
     */
    List<MeetPriceRules> getMeetPriceRuleByMeetNo(String meetNo);


}