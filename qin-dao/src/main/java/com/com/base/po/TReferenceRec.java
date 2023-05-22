package com.com.base.po;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
import lombok.Data;

@Table(name = "t_reference_rec")
@Data
public class TReferenceRec implements Serializable {
    /**
     * 主键ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 客户号
     */
    @Column(name = "cust_id")
    private String custId;

    /**
     * 客户名称
     */
    @Column(name = "cust_name")
    private String custName;

    /**
     * 客户身份证号
     */
    @Column(name = "id_no")
    private String idNo;

    /**
     * 客户手机号
     */
    @Column(name = "phone_no")
    private String phoneNo;

    /**
     * 抽奖编号
     */
    @Column(name = "lottery_no")
    private String lotteryNo;

    /**
     * 任务编号
     */
    @Column(name = "task_no")
    private String taskNo;

    /**
     * 获取方式；01-达标送抽奖；02-完成任务送抽奖
     */
    @Column(name = "lottery_add_type")
    private String lotteryAddType;

    /**
     * 抽奖次数
     */
    @Column(name = "lottery_times")
    private String lotteryTimes;

    /**
     * 抽奖开始时间
     */
    @Column(name = "begin_date")
    private Date beginDate;

    /**
     * 抽奖结束时间
     */
    @Column(name = "end_date")
    private Date endDate;

    /**
     * 页面状态；00：正常启动 01：结束 02：待启动
     */
    @Column(name = "lottery_status")
    private String lotteryStatus;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 更新时间
     */
    @Column(name = "update_time")
    private Date updateTime;

    private static final long serialVersionUID = 1L;
}