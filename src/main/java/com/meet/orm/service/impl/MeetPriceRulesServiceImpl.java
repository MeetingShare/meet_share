package com.meet.orm.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.meet.dto.req.MeetRulePriceReq;
import com.meet.exception.SystemException;
import com.meet.orm.dao.MeetPriceRulesMapper;
import com.meet.orm.pojo.MeetPriceRules;
import com.meet.orm.service.MeetPriceRulesService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by bzhx on 2017-11-05 20:11.
 */
@Service
public class MeetPriceRulesServiceImpl implements MeetPriceRulesService {

    @Autowired
    private MeetPriceRulesMapper meetPriceRulesMapper;

    @Override
    public PageInfo<MeetPriceRules> findMeetPriceRuleListPage(MeetRulePriceReq reqInfo) {
        PageHelper.startPage(reqInfo.getPageCurrent(), reqInfo.getPageSize());
        MeetPriceRules data=new MeetPriceRules();
        BeanUtils.copyProperties(reqInfo,data);
        List<MeetPriceRules> list = meetPriceRulesMapper.findAllMeetPriceRules(data);
        PageInfo<MeetPriceRules> pageInfo = new PageInfo<MeetPriceRules>(list);
        return pageInfo;
    }

    @Override
    public int addMeetPriceRule(MeetRulePriceReq reqInfo) throws Exception {
        //判断日期是否为空
        validateParam(reqInfo);
        //判断日期规则、开始时间是否存在
        validateStartTime(reqInfo);
        //判断日期规则、结束时间是否存在
        validateEndTime(reqInfo);
        MeetPriceRules data=new MeetPriceRules();
        BeanUtils.copyProperties(reqInfo,data);
        data.setCreateTime(new Date());
        return meetPriceRulesMapper.insertSelective(data);
    }

    @Override
    public int updateMeetPriceRule(MeetRulePriceReq reqInfo) throws Exception {
        //判断ID是否为空
        if(reqInfo.getId()==null){
            throw new SystemException("编号为空");
        }
        //判断日期是否为空
        validateParam(reqInfo);
        //判断日期规则、开始时间是否存在
        validateStartTime(reqInfo);
        //判断日期规则、结束时间是否存在
        validateEndTime(reqInfo);
        MeetPriceRules data=new MeetPriceRules();
        BeanUtils.copyProperties(reqInfo,data);
        data.setCreateTime(new Date());
        return meetPriceRulesMapper.updateByPrimaryKeySelective(data);
    }

    @Override
    public int delMeetPriceRule(Integer id) throws Exception {
        return meetPriceRulesMapper.deleteById(id);
    }

    @Override
    public MeetPriceRules getMeetPriceRuleById(Integer id) {
        return meetPriceRulesMapper.selectByPrimaryKey(id);
    }
    @Override
    public List<MeetPriceRules> getMeetPriceRuleByMeetNo(String meetNo) {
        MeetPriceRules  data=new MeetPriceRules();
        data.setMeetNo(meetNo);
        return meetPriceRulesMapper.findAllMeetPriceRules(data);
    }
    private void validateStartTime(MeetRulePriceReq reqInfo) throws SystemException{
        MeetPriceRules data=new MeetPriceRules();
        data.setMeetNo(reqInfo.getMeetNo());
        data.setRuleDate(reqInfo.getRuleDate());
        data.setStartTime(reqInfo.getStartTime());
        //判断日期规则、开始时间是否存在
        data = meetPriceRulesMapper.selectRuleIsExits(data);
        if(data!=null){
            throw new SystemException("日期："+reqInfo.getRuleDate()+",起始日期："+reqInfo.getStartTime()+"已存在");
        }
    }
    private void validateEndTime(MeetRulePriceReq reqInfo) throws SystemException{
        MeetPriceRules data=new MeetPriceRules();
        data.setMeetNo(reqInfo.getMeetNo());
        data.setRuleDate(reqInfo.getRuleDate());
        data.setStartTime(reqInfo.getEndTime());
        //判断日期规则、开始时间是否存在
        data = meetPriceRulesMapper.selectRuleIsExits(data);
        if(data!=null){
            throw new SystemException("日期："+reqInfo.getRuleDate()+",结束日期："+reqInfo.getEndTime()+"已存在");
        }
    }
    private void validateParam(MeetRulePriceReq reqInfo) throws SystemException{
        //判断日期是否为空
        if (StringUtils.isEmpty(reqInfo.getRuleDate())) {
            throw new SystemException("规则日期为空");
        }
        if (StringUtils.isEmpty(reqInfo.getStartTime())) {
            throw new SystemException("规则开始时间为空");
        }
        if (StringUtils.isEmpty(reqInfo.getEndTime())) {
            throw new SystemException("规则结束时间为空");
        }
        if(Double.parseDouble(reqInfo.getStartTime())>Double.parseDouble(reqInfo.getEndTime())){
            throw new SystemException("起始时间大于结束时间");
        }
        if(reqInfo.getUnitPrice()==null){
            throw new SystemException("定价为空");
        }
    }
}