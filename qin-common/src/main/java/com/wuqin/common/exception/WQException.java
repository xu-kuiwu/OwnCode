package com.wuqin.common.exception;

import com.wuqin.common.enums.WQErrorEnum;

public class WQException extends RuntimeException implements Messageable{
    private WQError error= new WQError();

    public WQException(String message){
        super(message);
        error.setCode(WQErrorEnum.FAILED.getCode());
    }

    @Override
    public int getCode() {
        return error.getCode();
    }
}
