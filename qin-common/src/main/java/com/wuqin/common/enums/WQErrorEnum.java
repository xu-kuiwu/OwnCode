package com.wuqin.common.enums;

public enum WQErrorEnum {
    FAILED(500,"操作失败"),
    VALID_FIELD_FAILED(100000,"字段校验失败");

    private final int code;
    private final String message;

    WQErrorEnum(int code,String message){
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage(){
        return message;
    }
}
