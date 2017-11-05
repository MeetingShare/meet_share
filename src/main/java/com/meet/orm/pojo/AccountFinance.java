package com.meet.orm.pojo;

import java.util.Date;

public class AccountFinance {
    private Integer id;

    private Integer uid;

    private Long advanceMoney;

    private Long blanceMoney;

    private String status;

    private Integer version;

    private Date createTime;

    private Date updateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Long getAdvanceMoney() {
        return advanceMoney;
    }

    public void setAdvanceMoney(Long advanceMoney) {
        this.advanceMoney = advanceMoney;
    }

    public Long getBlanceMoney() {
        return blanceMoney;
    }

    public void setBlanceMoney(Long blanceMoney) {
        this.blanceMoney = blanceMoney;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
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