package com.wuqin.admin.dto;

import lombok.Data;

import java.util.Date;

@Data
public class PupilInfoDto {
    private int id;

    /**
     * 学生编号
     */
    private String pupilNo;

    /**
     * 学生姓名
     */
    private String pupilName;

    /**
     * 年级
     */
    private String pupilClass;

    /**
     * 出生年月
     */
    private String birthDate;

    /**
     * 报名时间
     */
    private String enrollDate;

    /**
     * 家长
     */
    private String parentNo;

    /**
     * 教室
     */
    private String classroom;

    /**
     * 课程信息
     */
    private String courseInfo;

    /**
     * 课程开始日期
     */
    private String courseStart;

    /**
     * 课程结束日期
     */
    private String courseEnd;

    /**
     * 课时次数
     */
    private String classHourNum;

    /**
     * 状态；0，在场 1离场
     */
    private String status;

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
