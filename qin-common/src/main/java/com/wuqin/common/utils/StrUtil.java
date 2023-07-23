package com.wuqin.common.utils;

import cn.hutool.core.util.RandomUtil;
import com.wuqin.common.constants.CommonConstants;

import java.util.UUID;


public class StrUtil {
    public static String getUUID() {
        return UUID.randomUUID().toString().replaceAll(CommonConstants.SPLIT_LINE, CommonConstants.EMPTY);
    }

    public static String getNo(int count) {
        String yyyyDDmm = DateUtil.getDatetimeFormat();
        String random = RandomUtil.randomNumbers(count);
        return yyyyDDmm.concat(random);
    }

    public static String getPupilNo(int count) {
        String yyyyDDmm = DateUtil.getYYYYMMdd();
        yyyyDDmm = yyyyDDmm.substring(0, yyyyDDmm.length() -2);
        if (count < 10) {
            return yyyyDDmm.concat("0000").concat(String.valueOf(count));
        } else if (count < 100) {
            return yyyyDDmm.concat("000").concat(String.valueOf(count));
        } else if (count < 1000) {
            return yyyyDDmm.concat("00").concat(String.valueOf(count));
        } else if (count < 10000) {
            return yyyyDDmm.concat("0").concat(String.valueOf(count));
        }
        return yyyyDDmm.concat(String.valueOf(count));
    }

    public static String getTeacherNo(int count) {
        String yyyyDDmm = DateUtil.getYYYYMMdd();
        if (count < 10) {
            return yyyyDDmm.concat("0000").concat(String.valueOf(count));
        } else if (count < 100) {
            return yyyyDDmm.concat("000").concat(String.valueOf(count));
        } else if (count < 1000) {
            return yyyyDDmm.concat("00").concat(String.valueOf(count));
        } else if (count < 10000) {
            return yyyyDDmm.concat("0").concat(String.valueOf(count));
        }
        return yyyyDDmm.concat(String.valueOf(count));
    }
}
