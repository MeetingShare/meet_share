package com.meet.orm.pojo;

import java.math.BigDecimal;
import java.util.Date;

public class AccountReservateOrder {
    private Integer id;

    private String orderNo;

    private Integer uid;

    private String uname;

    private String uopenId;

    private String meetNo;

    private String meetName;

    private String meetDate;

    private String meetStartTime;

    private Integer meetLength;

    private String meetEndTime;

    private String meetTheme;

    private String meetPerson;

    private Integer reminding;

    private String isReminding;

    private BigDecimal money;

    private Integer totalTime;

    private String useStatus;

    private BigDecimal totalMoney;

    private String status;

    private Date payTime;

    private Date createTime;

    private Date updateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo == null ? null : orderNo.trim();
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname == null ? null : uname.trim();
    }

    public String getUopenId() {
        return uopenId;
    }

    public void setUopenId(String uopenId) {
        this.uopenId = uopenId == null ? null : uopenId.trim();
    }

    public String getMeetNo() {
        return meetNo;
    }

    public void setMeetNo(String meetNo) {
        this.meetNo = meetNo == null ? null : meetNo.trim();
    }

    public String getMeetName() {
        return meetName;
    }

    public void setMeetName(String meetName) {
        this.meetName = meetName == null ? null : meetName.trim();
    }

    public String getMeetDate() {
        return meetDate;
    }

    public void setMeetDate(String meetDate) {
        this.meetDate = meetDate == null ? null : meetDate.trim();
    }

    public String getMeetStartTime() {
        return meetStartTime;
    }

    public void setMeetStartTime(String meetStartTime) {
        this.meetStartTime = meetStartTime == null ? null : meetStartTime.trim();
    }

    public Integer getMeetLength() {
        return meetLength;
    }

    public void setMeetLength(Integer meetLength) {
        this.meetLength = meetLength;
    }

    public String getMeetEndTime() {
        return meetEndTime;
    }

    public void setMeetEndTime(String meetEndTime) {
        this.meetEndTime = meetEndTime == null ? null : meetEndTime.trim();
    }

    public String getMeetTheme() {
        return meetTheme;
    }

    public void setMeetTheme(String meetTheme) {
        this.meetTheme = meetTheme == null ? null : meetTheme.trim();
    }

    public String getMeetPerson() {
        return meetPerson;
    }

    public void setMeetPerson(String meetPerson) {
        this.meetPerson = meetPerson == null ? null : meetPerson.trim();
    }

    public Integer getReminding() {
        return reminding;
    }

    public void setReminding(Integer reminding) {
        this.reminding = reminding;
    }

    public String getIsReminding() {
        return isReminding;
    }

    public void setIsReminding(String isReminding) {
        this.isReminding = isReminding == null ? null : isReminding.trim();
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public Integer getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(Integer totalTime) {
        this.totalTime = totalTime;
    }

    public String getUseStatus() {
        return useStatus;
    }

    public void setUseStatus(String useStatus) {
        this.useStatus = useStatus == null ? null : useStatus.trim();
    }

    public BigDecimal getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(BigDecimal totalMoney) {
        this.totalMoney = totalMoney;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
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