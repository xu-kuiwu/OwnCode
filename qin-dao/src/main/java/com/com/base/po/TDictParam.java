package com.com.base.po;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
import lombok.Data;

@Table(name = "t_dict_param")
@Data
public class TDictParam implements Serializable {
    /**
     * 主键ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 参数编码
     */
    @Column(name = "dict_code")
    private String dictCode;

    /**
     * 参数名称
     */
    @Column(name = "dict_name")
    private String dictName;

    /**
     * 参数描述
     */
    @Column(name = "dict_desc")
    private String dictDesc;

    /**
     * 显示顺序
     */
    @Column(name = "dict_order")
    private String dictOrder;

    /**
     * 如果为0时则是顶级参数
     */
    private String pcode;

    /**
     * 1有效；0无效，默认1
     */
    @Column(name = "dict_status")
    private String dictStatus;

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