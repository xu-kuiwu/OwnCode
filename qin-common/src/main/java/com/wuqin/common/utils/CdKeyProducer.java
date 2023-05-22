package com.wuqin.common.utils;

import lombok.Data;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * 兑换码生成器
 * @author admin
 * @since v20220913
 */
public class CdKeyProducer {
    private CdKeyProducer(){
        //do nothing
    }

    private static char[] chars = "2AfB7CdD5E8eFG9HyJK34MhZNP6QrRSTU".toCharArray();

    /**
     * 快速定位元素及下标
     */
    private static final Map<Character, Integer> characters = new HashMap<>();

    static {
        for(int i=0;i < chars.length;i++){
            characters.put(chars[i],i);
        }
    }

    /**
     * 映射
     */
    private static final Map<Object,Object> VERSION_SALT_MAP = ArrayUtils.toMap(new String[][]{
            {"0","!@#$%^&*()"},{"1","@#$%^&*()!"},{"2","*()!@#$%^&"},{"3","()*&^%$#@!"},
            {"4","1234567890"},{"5","2345678901"},{"6","8901234567"},{"7","0987654321"},
            {"8","QWERTYUIOP"},{"9","WERTYUIOPQ"},{"10","OPQWERTYUI"},{"11","POIUYTREWQ"},
            {"12","ASDFGHJKLZ"},{"13","SDFGHJKLZA"},{"14","LZASDFGHJK"},{"15","ZLKJHGFDSA"},
            {"16","MNBVCXZAQW"},{"17","NBVCXZAQWM"},{"18","QWMNBVCXZA"},{"19","WQAZXCVBNM"},
            {"20","qwertyuiop"},{"21","wertyuiopq"},{"22","iopqwertyu"},{"23","poiuytrewq"},
            {"24","asdfghjklz"},{"25","sdfghjklza"},{"26","klzasdfghj"},{"27","zlkjhgfdsa"},
            {"28","zxcvbnmlpo"},{"29","xcvbnmlpoz"},{"30","lpozxcvbnm"},{"31","oplmnbvcxz"},
            {"32","+=:;,.<>?|"}
    });

    /**
     * 生成兑换码
     * 验证不通过，抛出IllegalArgumentException
     *
     * @param message 消息信息
     * @return 兑换码
     * IllegalArgumentException 验证不通过时会抛出
     */
    public static String encode(CdKeyMessage message){
        message.version = new Random().nextInt(32);

        //参数校验
        checkParam(message);

        int[] index = new int[14];
        index[0] = message.version & 0X1F;
        index[1] = ((message.year - 2022) >>> 1) & 0X1F;
        index[2] = (((message.year - 2022) & 0X1) << 4) | (message.month & 0XF);
        index[3] = message.day & 0X1F;
        index[4] = (message.activityNo >>> 5) & 0X1F;
        index[5] = message.activityNo & 0X1F;
        index[6] = (message.cdKeyNo >>> 15) & 0X1F;
        index[7] = (message.cdKeyNo >>> 10) & 0X1F;
        index[8] = (message.cdKeyNo >>> 5) & 0X1F;
        index[9] = message.cdKeyNo & 0X1F;

        String salt = "" + message.day + message.activityNo + message.cdKeyNo
                + VERSION_SALT_MAP.get(String.valueOf(message.version));

        index[10] = MD5Util.getMD5(salt).getBytes()[0] & 0X1F;
        index[11] = MD5Util.getMD5(salt).getBytes()[1] & 0X1F;
        index[12] = MD5Util.getMD5(salt).getBytes()[2] & 0X1F;
        index[13] = MD5Util.getMD5(salt).getBytes()[3] & 0X1F;


        StringBuilder builder = new StringBuilder();
        int offset = new Random().nextInt(32);
        builder.append(chars[offset]);
        for(int i = 0;i < index.length; i++){
            if((i & 0X1) == 0){
                //偶数往右移
                builder.append(chars[(index[i] + offset) > 31 ? index[i] + offset - 32 : index[i] + offset]);
            } else {
                //奇数往左移
                builder.append(chars[(index[i] - offset) < 0 ? index[i] - offset + 32 : index[i] - offset]);
            }
        }

        return builder.toString();
    }

    public static CdKeyMessage decode(String cdKey){
        //参数验证
        checkCdKey(cdKey);

        StringBuilder builder = new StringBuilder(cdKey);

        CdKeyMessage message = new CdKeyMessage();

        int offset = characters.get(builder.charAt(0));
        builder.deleteCharAt(0);

        //版本
        message.version = intValue(builder, 0, offset);

        //年
        message.year = ((intValue(builder, 1, offset) << 1) | ((intValue(builder, 2, offset) >>> 4) & 0X1)) + 2022;

        //月
        message.month = intValue(builder, 2, offset) & 0XF;

        //日
        message.day = intValue(builder, 3, offset) & 0X1F;

        //活动序号
        message.activityNo = ((intValue(builder, 4, offset) & 0X1F) << 5) | (intValue(builder, 5, offset) & 0X1F);

        //兑换码序号
        message.cdKeyNo = ((intValue(builder, 6, offset) & 0X1F) << 15) | ((intValue(builder, 7, offset) & 0X1F) << 10)
                | ((intValue(builder, 8, offset) & 0X1F) << 5) | (intValue(builder, 9, offset) & 0X1F);

        String salt = "" + message.day + message.activityNo + message.cdKeyNo
                + VERSION_SALT_MAP.get(String.valueOf(message.version));

        boolean one = (MD5Util.getMD5(salt).getBytes()[0] & 0X1F) == intValue(builder, 10, offset);
        boolean two = (MD5Util.getMD5(salt).getBytes()[1] & 0X1F) == intValue(builder, 11, offset);
        boolean three = (MD5Util.getMD5(salt).getBytes()[2] & 0X1F) == intValue(builder, 12, offset);
        boolean four = (MD5Util.getMD5(salt).getBytes()[3] & 0X1F) == intValue(builder, 13, offset);

        if(one && two && three && four){
            return message;
        } else {
            throw new IllegalArgumentException("未通过验证！！");
        }
    }

    /**
     * 参数校验
     *
     * @param message 消息体
     */
    private static void checkParam(CdKeyMessage message){
        //版本
        boolean versionValid = message.version >= 0 && message.version < 32;

        //年
        boolean yearValid = message.year >= 2022 && message.year < 2022 + 100;

        //月
        boolean monthValid = message.month > 0 && message.month < 13;

        //日
        boolean dayValid = message.day > 0 && message.day < 32;

        //活动序号
        boolean activityNoValid = message.activityNo > 0 && message.activityNo <= 0X3FF;

        //兑换码序号
        boolean cdKeyNoValid = message.cdKeyNo > 0 && message.cdKeyNo <= 0XFFFFF;

        //是否通过
        if(!(versionValid && yearValid && monthValid && dayValid && activityNoValid && cdKeyNoValid)){
            throw new IllegalArgumentException("参数校验失败！！");
        }
    }

    private static void checkCdKey(String cdKey){
        if(StringUtils.isEmpty(cdKey) || cdKey.length() != 15){
            throw new IllegalArgumentException("兑换码格式不正确！！");
        }
        char[] charArray = cdKey.toCharArray();
        for(char c : charArray){
            if(characters.get(c) == null){
                throw new IllegalArgumentException("兑换码格式非法！！");
            }
        }
    }

    /**
     *  计算字符对于的整数值
     *
     * @param builder 字符数组
     * @param index 下标
     * @param offset 偏移量
     * @return 字符对应的整数型
     */
    private static int intValue(StringBuilder builder, int index, int offset){
        int res = characters.get(builder.charAt(index));
        if ((index & 0X1) == 0){
            //偶数
            return res - offset >= 0 ? res - offset : res + 32 - offset;
        } else {
            //奇数
            return res + offset > 31 ? res + offset - 32 : res + offset;
        }
    }

    @Data
    public static class CdKeyMessage implements Serializable{
        /**
         * 版本号
         */
        private int version = -1;
        /**
         * 年，2022
         */
        private int year = -1;
        /**
         * 月，1-12
         */
        private int month = -1;
        /**
         * 日，1-31
         */
        private int day = -1;
        /**
         * 活动序号
         */
        private int activityNo = -1;
        /**
         * 兑换码序号
         */
        private int cdKeyNo = -1;

        /**
         * 重新组装码对象
         * @param batchNo 获得编号 前3位不做处理
         * @param serNo
         */
        public void of(String batchNo, int serNo){
            this.year = Integer.parseInt(batchNo.substring(3,7));
            this.month = Integer.parseInt(batchNo.substring(7,9));
            this.day = Integer.parseInt(batchNo.substring(9,11));
            this.activityNo = Integer.parseInt(batchNo.substring(11));
            this.cdKeyNo = serNo;
        }

        public String getBatchNo(){
            //填充成两位
            String monStr = StringUtils.leftPad(String.valueOf(month),2,'0');
            String dayStr = StringUtils.leftPad(String.valueOf(day),2,'0');
            String serNo = StringUtils.leftPad(String.valueOf(activityNo),8,'0');
            return "PCH" + year + monStr + dayStr + serNo;
        }

        @Override
        public boolean equals(Object oj){
            if(this == oj){
                return true;
            }
            if(null == oj || getClass() != oj.getClass()){
                return false;
            }

            CdKeyMessage message = (CdKeyMessage) oj;

            if(version != message.version){
                return false;
            }

            if(year != message.year){
                return false;
            }

            if(month != message.month){
                return false;
            }

            if(day != message.day){
                return false;
            }

            if(activityNo != message.activityNo){
                return false;
            }
            return cdKeyNo == message.cdKeyNo;
        }

        @Override
        public int hashCode(){
            int result = version;
            result = 31 * result + year;
            result = 31 * result + month;
            result = 31 * result + day;
            result = 31 * result + activityNo;
            result = 31 * result + cdKeyNo;
            return result;
        }

        @Override
        public String toString(){ return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE); };
    }
}
