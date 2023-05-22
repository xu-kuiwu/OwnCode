package com.wuqin.common.enums;

public enum ResponseEnum implements StatusEnum{
    OPERATOR_SUCCESS("0000","操作成功"),
    SYSTEM_ERROR("9999","系统异常")
    ;

    private String code;

    private String desc;

    private ResponseEnum(String code,String desc){
        this.code = code;
        this.desc = desc;
    }

    @Override
    public String getCode() {
        return this.code;
    }

    @Override
    public String getDesc() {
        return this.desc;
    }
}
