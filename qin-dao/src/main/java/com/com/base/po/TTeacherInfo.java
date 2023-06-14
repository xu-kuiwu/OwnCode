package com.com.base.po;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
import lombok.Data;

@Table(name = "t_teacher_info")
@Data
public class TTeacherInfo implements Serializable {
    /**
     * 主键ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 编号
     */
    @Column(name = "staff_no")
    private String staffNo;

    /**
     * 姓名
     */
    @Column(name = "teacher_name")
    private String teacherName;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 学历
     */
    @Column(name = "DEGREE")
    private String degree;

    /**
     * 毕业院校
     */
    @Column(name = "graduated_from")
    private String graduatedFrom;

    /**
     * 出生年月
     */
    @Column(name = "birth_date")
    private String birthDate;

    /**
     * 薪资
     */
    private String salary;

    /**
     * 状态;0在职 1离职
     */
    private String status;

    /**
     * 岗位;0全职 1兼职 9其他
     */
    private String post;

    /**
     * 教师资格证;0无 1有 9其他
     */
    @Column(name = "certification_flag")
    private String certificationFlag;

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