package com.meet.dto.req;

import lombok.Data;

/**
 * Created by bzhx on 2017-11-04 9:56.
 */
@Data
public class MeetingInfoReq extends SysRequest {

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

    // 商品对应图片数据
    private String imageIds;
    // 是否默认 图片ID-1
    private String imageDefault;
}