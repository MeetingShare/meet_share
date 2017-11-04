package com.meet.orm.pojo;

import java.util.Date;
import java.util.List;

public class MeetingInfo {
    private Integer id;

    private String meetNo;

    private String lockNo;

    private String name;

    private String area;

    private Integer seatingCount;

    private String city;

    private String province;

    private String address;

    private String type;

    private String supportFacilities;

    private String status;

    private Integer minTime;

    private String startTime;

    private String endTime;

    private Double lat;

    private Double lng;

    private String position;

    private String remark;

    private Integer version;

    private String isDelete;

    private Date createTime;

    private Date updateTime;
    // 默认图片
    private String path;
    private String httpUrl;

    //图片字符串
    private String imageId;
    //两个坐标之间距离
    private String distance;

    List<ImageFile> images;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getHttpUrl() {
        return httpUrl;
    }

    public void setHttpUrl(String httpUrl) {
        this.httpUrl = httpUrl;
    }

    public String getImageId() {
        return imageId;
    }

    public void setImageId(String imageId) {
        this.imageId = imageId;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public List<ImageFile> getImages() {
        return images;
    }

    public void setImages(List<ImageFile> images) {
        this.images = images;
    }

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

    public String getLockNo() {
        return lockNo;
    }

    public void setLockNo(String lockNo) {
        this.lockNo = lockNo == null ? null : lockNo.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area == null ? null : area.trim();
    }

    public Integer getSeatingCount() {
        return seatingCount;
    }

    public void setSeatingCount(Integer seatingCount) {
        this.seatingCount = seatingCount;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province == null ? null : province.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getSupportFacilities() {
        return supportFacilities;
    }

    public void setSupportFacilities(String supportFacilities) {
        this.supportFacilities = supportFacilities == null ? null : supportFacilities.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public Integer getMinTime() {
        return minTime;
    }

    public void setMinTime(Integer minTime) {
        this.minTime = minTime;
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

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLng() {
        return lng;
    }

    public void setLng(Double lng) {
        this.lng = lng;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position == null ? null : position.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public String getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(String isDelete) {
        this.isDelete = isDelete == null ? null : isDelete.trim();
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