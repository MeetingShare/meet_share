package com.meet.orm.pojo;

import java.util.Date;

public class MeetingTimeInfo {
    private Integer id;

    private String meetNo;

    private String orderNo;

    private String meetingDate;

    private String meetStatus;

    private String meetStartTime;

    private Integer meetLength;

    private String meetEndTime;

    private Integer version;

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

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo == null ? null : orderNo.trim();
    }

    public String getMeetingDate() {
        return meetingDate;
    }

    public void setMeetingDate(String meetingDate) {
        this.meetingDate = meetingDate == null ? null : meetingDate.trim();
    }

    public String getMeetStatus() {
        return meetStatus;
    }

    public void setMeetStatus(String meetStatus) {
        this.meetStatus = meetStatus == null ? null : meetStatus.trim();
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

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
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