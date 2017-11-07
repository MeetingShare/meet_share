package com.meet.dto.req;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by bzhx on 2017-11-07 20:16.
 */
@Data
public class AccountReservateOrderReq extends SysRequest{

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


}