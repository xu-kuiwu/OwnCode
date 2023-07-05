package com.wuqin.admin.dto;

import lombok.Data;

import java.util.Date;

@Data
public class CourseInfoDto {
    /**
     * 课程编号
     */
    private String courseNo;

    /**
     * 课程名称
     */
    private String courseName;

    /**
     * 课程费用
     */
    private String fee;

    /**
     * 课时
     */
    private String classHour;

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
