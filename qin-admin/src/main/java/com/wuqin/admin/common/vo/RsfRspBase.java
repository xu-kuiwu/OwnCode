package com.wuqin.admin.common.vo;

import lombok.Data;

import java.util.Map;

@Data
public class RsfRspBase {
    private Map<String, Object> head;

    private Map<String, Object> body;
}
