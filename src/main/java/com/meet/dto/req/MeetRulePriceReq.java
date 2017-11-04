package com.meet.dto.req;

import lombok.Data;

import java.math.BigDecimal;

/**
 * Created by bzhx on 2017-11-04 17:43.
 */
@Data
public class MeetRulePriceReq extends SysRequest {
    private Integer id;

    private String meetNo;

    private String ruleDate;

    private Float startTime;

    private Float endTime;

    private BigDecimal unitPrice;
}