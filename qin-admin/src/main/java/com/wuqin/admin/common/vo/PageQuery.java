package com.wuqin.admin.common.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel(value = "分页查询条件对象", description = "分页查询条件对象")
public class PageQuery<T> implements Serializable {
    private static final long serialVersionUID = 3147286352961012925L;
    @ApiModelProperty(value = "煤业数据条数")
    private int limit = 10;
    @ApiModelProperty(value = "页数")
    private int page = 10;
    @ApiModelProperty(value = "查询条件")
    private T data;
}
