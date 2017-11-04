package com.meet.orm.pojo;

import java.math.BigDecimal;
import java.util.Date;

public class MeetPriceRules {
    private Integer id;

    private String meetNo;

    private String ruleDate;

    private Float startTime;

    private Float endTime;

    private BigDecimal unitPrice;

    private Date createTime;

    private Date updateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMeetNo() {
        return meetNo;
    }

    public void setMeetNo(String meetNo) {
        this.meetNo = meetNo == null ? null : meetNo.trim();
    }

    public String getRuleDate() {
        return ruleDate;
    }

    public void setRuleDate(String ruleDate) {
        this.ruleDate = ruleDate == null ? null : ruleDate.trim();
    }

    public Float getStartTime() {
        return startTime;
    }

    public void setStartTime(Float startTime) {
        this.startTime = startTime;
    }

    public Float getEndTime() {
        return endTime;
    }

    public void setEndTime(Float endTime) {
        this.endTime = endTime;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}