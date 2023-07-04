package com.com.base.po;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
import lombok.Data;

@Table(name = "t_course_info")
@Data
public class TCourseInfo implements Serializable {
    /**
     * 主键ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 课程编号
     */
    @Column(name = "course_no")
    private String courseNo;

    /**
     * 课程名称
     */
    @Column(name = "course_name")
    private String courseName;

    /**
     * 课程费用
     */
    private String fee;

    /**
     * 课时
     */
    @Column(name = "class_hour")
    private String classHour;

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