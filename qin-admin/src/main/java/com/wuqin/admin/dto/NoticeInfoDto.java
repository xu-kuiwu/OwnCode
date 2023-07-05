package com.wuqin.admin.dto;

import lombok.Data;

import java.util.Date;

@Data
public class NoticeInfoDto {
    /**
     * 公告编号
     */
    private String noticeNo;

    /**
     * 公告标题
     */
    private String noticeTitle;

    /**
     * 公告内容
     */
    private String noticeContent;

    /**
     * 公告日期
     */
    private String noticeDate;

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
