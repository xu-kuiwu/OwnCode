package com.com.base.po;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
import lombok.Data;

@Table(name = "t_pupil_info")
@Data
public class TPupilInfo implements Serializable {
    /**
     * 主键ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 学生编号
     */
    @Column(name = "pupil_no")
    private String pupilNo;

    /**
     * 学生姓名
     */
    @Column(name = "pupil_name")
    private String pupilName;

    /**
     * 年级
     */
    @Column(name = "pupil_class")
    private String pupilClass;

    /**
     * 出生年月
     */
    @Column(name = "birth_date")
    private String birthDate;

    /**
     * 报名时间
     */
    @Column(name = "enroll_date")
    private String enrollDate;

    /**
     * 家长
     */
    @Column(name = "parent_no")
    private String parentNo;

    /**
     * 教室
     */
    private String classroom;

    /**
     * 课程信息
     */
    @Column(name = "course_info")
    private String courseInfo;

    /**
     * 课程开始日期
     */
    @Column(name = "course_start")
    private String courseStart;

    /**
     * 课程结束日期
     */
    @Column(name = "course_end")
    private String courseEnd;

    /**
     * 课时次数
     */
    @Column(name = "class_hour_num")
    private String classHourNum;

    /**
     * 状态；0，在场 1离场
     */
    private String status;

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