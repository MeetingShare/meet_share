package com.meet.dto.req;

import lombok.Data;

/**
 * Created by bzhx on 2017-11-04 13:59.
 */
@Data
public class AccountFeedbackInfoReq extends SysRequest{
    private Integer id;

    private Integer uid;

    private String backContent;
}