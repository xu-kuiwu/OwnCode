package com.wuqin.common.log;

import lombok.Data;

import java.io.Serializable;

@Data
public class MonitorInfo implements Serializable {
    private String system;
    private String method;
    private String code = "0000";
    private String bizErr;
    private String msg = "success";
    private long time;
    private String sysErr;
}
