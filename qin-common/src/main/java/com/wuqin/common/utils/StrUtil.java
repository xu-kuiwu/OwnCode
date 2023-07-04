package com.wuqin.common.utils;

import com.wuqin.common.constants.CommonConstants;

import java.util.UUID;


public class StrUtil {
    public static String getUUID() {
        return UUID.randomUUID().toString().replaceAll(CommonConstants.SPLIT_LINE, CommonConstants.EMPTY);
    }

    public static String getParentNo(int count) {
        String yyyyDDmm = DateUtil.getYYYYMMdd();
        if (count < 10) {
            return yyyyDDmm.concat("000").concat(String.valueOf(count));
        } else if (count < 100) {
            return yyyyDDmm.concat("00").concat(String.valueOf(count));
        } else if (count < 1000) {
            return yyyyDDmm.concat("0").concat(String.valueOf(count));
        }
        return yyyyDDmm.concat(String.valueOf(count));
    }
}
