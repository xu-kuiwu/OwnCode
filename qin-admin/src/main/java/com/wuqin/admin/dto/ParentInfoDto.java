package com.wuqin.admin.dto;

import java.util.Date;

public class ParentInfoDto {
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

    /**
     * 住址
     */
    private String address;

    /**
     * 费用
     */
    private String fee;

    /**
     * 是否报名;0咨询 1报名 2已缴费 9已退款
     */
    private String enrollFlag;

    /**
     * 推荐人
     */
    private String referrer;

    /**
     * 推荐人手机号
     */
    private String referrerPhone;

    /**
     * 备注
     */
    private String remark;

    /**
     * 创建人
     */
    private String createNo;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改人
     */
    private String updateNo;

    /**
     * 更新时间
     */
    private Date updateTime;
}
