package com.wuqin.common.utils;

import cn.hutool.core.util.RandomUtil;

import java.sql.Time;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * 处理公共方法
 *
 * @author xukw
 */
public class CommUtil {

    public static Random r = new Random();

    public static String createActNo(String channelNo) {
        StringBuffer str = new StringBuffer();
        str.append(channelNo);
        str.append(timeToString());
        str.append(sixRandomCreate());
        return str.toString();
    }

    public static String twoRandomCreate() {
        Integer rand = (int) (Math.random() * 100);
        String value = String.valueOf(rand);
        for (int i = 0; i < 2 - value.length(); i++) {
            value = "0" + value;
        }
        return value;
    }

    /**
     * 生成6位随机数
     *
     * @return
     */
    private static String sixRandomCreate() {
        Integer i = (int) ((Math.random() * 9 + 1) * 1000);
        return i.toString();
    }

    /**
     * 生成10位随机数
     *
     * @return
     */
    public static String tenRandowCreate() {
        long num = Math.abs(r.nextLong() % 10000000000L);
        String s = String.valueOf(num);
        for (int i = 0; i < 10 - s.length(); i++) {
            s = "0" + s;
        }
        return s;
    }

    /**
     * 日期转字符串
     *
     * @return
     */
    private static String timeToString() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        return sdf.format(new Date(System.currentTimeMillis()));
    }

    public static String timeToString2() {
        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        return sdf.format(new Date(System.currentTimeMillis()));
    }

    /**
     * 字符串转日期
     *
     * @param time
     * @return
     */
    public static Date strToTime(String time) {
        Date date = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            date = sdf.parse(time);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return date;
    }

    public static Date strToDate(Object time) {
        if (time == null || "".equals(time)) {
            return null;
        }
        String dateTime = time.toString();
        Date date = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            date = sdf.parse(dateTime);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return date;

    }

    /**
     * 字符串 转 Timestamp
     *
     * @param time
     * @return
     */
    public static Timestamp strToTime2(String time) {
        if (time == null || "".equals(time)) {
            return null;
        }
        Timestamp ts = new Timestamp(System.currentTimeMillis());
        return ts.valueOf(time);
    }

    /**
     * 字符串 转 time
     *
     * @param time
     * @return
     */
    public static Time strToTime3(String time) {
        if (time == null || "".equals(time)) {
            return null;
        }
        Time ti = new Time(System.currentTimeMillis());
        return ti.valueOf(time);
    }

    /**
     * 证件类型
     *
     * @param idType
     * @return
     */
    public static String getIdType(String idType) {
        switch (idType) {
            case "P00":
                return "身份证";
            case "P05":
                return "港澳居民来往内地通行证";
            case "P06":
                return "中国护照";
            case "P07":
                return "户口簿";
            case "P09":
                return "永久居留证";
            case "P10":
                return "临时身份证";
            case "P12":
                return "台湾居民往来大陆通行证";
            case "P23":
                return "外国护照";
            case "P99":
                return "其他";
            default:
                return "";
        }
    }

    /**
     * 将到分的数字保留2位，并转换为分的正整数
     * 例：55.55，转化为5555
     *
     * @param num
     * @return
     */
    public static Long getNums(Double num) {
        return Long.parseLong(String.format("%.2f", num).replace(".", ""));
    }

    public static String createCommonNo(String type) {
        StringBuffer str = new StringBuffer();
        str = str.append(type);
        str = str.append(timeToString());
        str = str.append(RandomUtil.randomNumbers(2));
        return str.toString();
    }
}
