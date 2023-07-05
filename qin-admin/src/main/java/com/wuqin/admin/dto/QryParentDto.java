package com.wuqin.admin.dto;

import lombok.Data;

@Data
public class QryParentDto {
    /**
     * 家长编号
     */
    private String parentNo;
    /**
     * 家长姓名
     */
    private String parentName;

    /**
     * 手机号
     */
    private String phone;
}
