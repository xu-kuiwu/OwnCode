package com.wuqin.common.utils;

import lombok.extern.slf4j.Slf4j;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@Slf4j
public class DateUtil {
    /**
     * yyyy-MM-dd HH:mm:ss.SSS
     */
    public static final String YEAR_MONTH = "yyyyMM";
    public static final String YYYYMMDD = "yyyyMMdd";
    public static final String YYYYMMDDHHMMSS = "yyyyMMddHHmmss";
    public static final String YYYYMMDD_ROW = "yyyy-MM-dd";
    public static final String DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public static final String DATETIME_FORMAT_S = "yyyy-MM-dd HH:mm:ss.SSS";

    public static final SimpleDateFormat sdf_ymd = new SimpleDateFormat(YYYYMMDD);
    public static final SimpleDateFormat sdf_ymds = new SimpleDateFormat(YYYYMMDDHHMMSS);

    public static String getYYYYMMdd() {
        return sdf_ymd.format(new Date());
    }

    public static String getDatetimeFormat() {
        return sdf_ymds.format(new Date());
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
        SimpleDateFormat sdf = new SimpleDateFormat(DATETIME_FORMAT);
        try {
            return sdf.parse(sdf.format(new Date()));
        } catch (Exception e) {
            log.error("getCurrentDate  exception", e);
        }
        return new Date();
    }

    /**
     * 获取字符型日期
     *
     * @return
     */
    public static String timeToString() {
        return sdf_ymds.format(new Date(System.currentTimeMillis()));
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
