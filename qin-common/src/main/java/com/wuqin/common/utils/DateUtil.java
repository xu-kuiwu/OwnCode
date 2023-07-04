package com.wuqin.common.utils;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {
    /**
     * yyyy-MM-dd HH:mm:ss.SSS
     */
    public static final String YEAR_MONTH = "yyyyMM";
    public static final String yyyyMMdd = "yyyyMMdd";
    public static final String yyyyMMdd_ROW = "yyyy-MM-dd";
    public static final String DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public static final String DATETIME_FORMAT_S = "yyyy-MM-dd HH:mm:ss.SSS";

    public static String getYYYYMMdd() {
        SimpleDateFormat sdf = new SimpleDateFormat(yyyyMMdd);
        return sdf.format(new Date());
    }

    /**
     * 获取当前系统时间
     *
     * @return
     */
    public static Timestamp getSystemTime() {
        return new Timestamp(System.currentTimeMillis());
    }

    public static String dateToStr(Date date, String form) {
        SimpleDateFormat sdf = new SimpleDateFormat(form);
        return sdf.format(date);
    }


    /**
     * 获取当前系统时间
     * 时间格式：yyyy-MM-dd HH:mm:ss
     *
     * @return
     */
    public static Date getCurrentDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return java.sql.Date.valueOf(sdf.format(new Date()));
    }

    /**
     * 获取指定日期的前N月；格式yyyyMM
     *
     * @param m
     * @return
     */
    public static final String getYMonth(int m) {
        SimpleDateFormat sdf = new SimpleDateFormat(YEAR_MONTH);
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) + 1);
        return sdf.format(calendar.getTime());
    }

    /**
     * 比较日期大小
     *
     * @param dateStr1
     * @param dateStr2
     * @param formatter
     * @return
     */
    public static final int compareDate(String dateStr1, String dateStr2, String formatter) {
        final SimpleDateFormat sdf = new SimpleDateFormat(formatter);
        try {
            final long date1 = sdf.parse(dateStr1).getTime();
            final long date2 = sdf.parse(dateStr2).getTime();
            if (date1 < date2) {
                return 1;
            }
            if (date1 > date1) {
                return -1;
            }
        } catch (Exception e) {
            return -2;
        }
        return 0;
    }

    /**
     * 时间 转 string
     *
     * @param date
     * @return
     */
    public static final String string2Date(Date date) {

        return "";
    }

    /**
     * string 转 时间
     *
     * @param str
     * @return
     */
    public static final Date date2String(String str) {

        return new Date();
    }
}
