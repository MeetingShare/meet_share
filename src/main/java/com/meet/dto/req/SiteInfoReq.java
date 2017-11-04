package com.meet.dto.req;

import lombok.Data;

/**
 * Created by bzhx on 2017-11-04 14:42.
 */
@Data
public class SiteInfoReq extends SysRequest {
    private Integer id;

    private String title;

    private String type;

    private String content;
}