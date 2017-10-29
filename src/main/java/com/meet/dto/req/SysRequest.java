package com.meet.dto.req;

import lombok.Data;

@Data
public class SysRequest {
    //每頁顯示條數
    private int pageSize=10;
    //當前頁數
    private int pageCurrent=1;
}
