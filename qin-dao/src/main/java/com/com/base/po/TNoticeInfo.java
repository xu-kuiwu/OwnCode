package com.com.base.po;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
import lombok.Data;

@Table(name = "t_notice_info")
@Data
public class TNoticeInfo implements Serializable {
    /**
     * 主键ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 公告编号
     */
    @Column(name = "notice_no")
    private String noticeNo;

    /**
     * 公告标题
     */
    @Column(name = "notice_title")
    private String noticeTitle;

    /**
     * 公告内容
     */
    @Column(name = "notice_content")
    private String noticeContent;

    /**
     * 公告日期
     */
    @Column(name = "notice_date")
    private String noticeDate;

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