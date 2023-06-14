package com.com.base.po;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
import lombok.Data;

@Table(name = "t_parent_info")
@Data
public class TParentInfo implements Serializable {
    /**
     * 主键ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 家长编号
     */
    @Column(name = "parent_no")
    private String parentNo;

    /**
     * 家长姓名
     */
    @Column(name = "parent_name")
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
    @Column(name = "enroll_flag")
    private String enrollFlag;

    /**
     * 推荐人
     */
    @Column(name = "Referrer")
    private String referrer;

    /**
     * 推荐人手机号
     */
    @Column(name = "Referrer_phone")
    private String referrerPhone;

    /**
     * 备注
     */
    private String remark;

    /**
     * 创建人
     */
    @Column(name = "create_no")
    private String createNo;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 修改人
     */
    @Column(name = "update_no")
    private String updateNo;

    /**
     * 更新时间
     */
    @Column(name = "update_time")
    private Date updateTime;

    private static final long serialVersionUID = 1L;
}