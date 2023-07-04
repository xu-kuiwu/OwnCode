package com.wuqin.admin.dto;

import java.util.Date;

public class TeacherInfoDto {
    /**
     * 编号
     */
    private String staffNo;

    /**
     * 姓名
     */
    private String teacherName;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 学历
     */
    private String degree;

    /**
     * 毕业院校
     */
    private String graduatedFrom;

    /**
     * 出生年月
     */
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
    private String certificationFlag;

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
