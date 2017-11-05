package com.meet.orm.pojo;

import java.math.BigDecimal;
import java.util.Date;

public class MeetPriceRules {
    private Integer id;

    private String meetNo;

    private String ruleDate;

    private String startTime;

    private String endTime;

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

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime == null ? null : startTime.trim();
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime == null ? null : endTime.trim();
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