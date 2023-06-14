package com.wuqin.admin.common.vo;

import com.wuqin.common.constants.Constant;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@ApiModel(value = "接口返回对象", description = "接口返回对象")
@Data
public class ResultVo<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "成功标志")
    private boolean success = true;

    @ApiModelProperty(value = "返回处理消息")
    private String message = "操作成功";

    @ApiModelProperty(value = "返回代码")
    private Integer newCode = Constant.SC_OK_200;

    @ApiModelProperty(value = "新返回代码")
    private String code = Constant.Code_success;

    @ApiModelProperty(value = "返回数据对象")
    private T data;

    @ApiModelProperty(value = "分页查询数据总条数")
    private Long total;

    @ApiModelProperty(value = "时间戳")
    private long timestamp = System.currentTimeMillis();

    public ResultVo(){}

    public ResultVo(T data) {this.data = data;}

    public static ResultVo<Object> success(){
        ResultVo<Object> r = new ResultVo<Object>();
        r.setSuccess(true);
        r.setCode(Constant.Code_success);
        r.setMessage("成功");
        return r;
    }

    public static ResultVo<Object> success(Object data){
        ResultVo<Object> r = new ResultVo<Object>(data);
        r.setSuccess(true);
        r.setCode(Constant.Code_success);
        r.setMessage("成功");
        return r;
    }

    public ResultVo<T> error500(String message){
        this.message = message;
        this.code = Constant.SERVER_ERROR;
        return this;
    }
}
