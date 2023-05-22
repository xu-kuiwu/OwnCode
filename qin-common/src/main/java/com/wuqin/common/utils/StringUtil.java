package com.wuqin.common.utils;

import com.wuqin.common.constants.CommonConstants;

import java.util.UUID;


public class StringUtil {
    public static String getUUID(){
        return UUID.randomUUID().toString().replaceAll(CommonConstants.SPLIT_LINE,CommonConstants.EMPTY);
    }
}
