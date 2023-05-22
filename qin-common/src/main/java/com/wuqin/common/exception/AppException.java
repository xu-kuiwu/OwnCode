package com.wuqin.common.exception;

import com.wuqin.common.enums.StatusEnum;
import lombok.Data;

@Data
public class AppException extends RuntimeException{

    private static final long serialVersionUID = 5835323708968291445L;

    /**
     * 错误编码
     */
    private final String code;

    private final String codeDesc;

    public AppException(StatusEnum StatusEnum, String errorMsg,Throwable t){
        super(errorMsg,t);

        this.code = StatusEnum.getCode();

        this.codeDesc = StatusEnum.getDesc();
    }
}
