package com.wuqin.common.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.DigestUtils;

import java.security.MessageDigest;

@Slf4j
public class MD5Util {
    private static final char[] HEX_CHARS = "0123456789abcdef".toCharArray();

    public MD5Util(){

    }

    public static String getMD5(String text){
        try {
            MessageDigest digest = MessageDigest.getInstance("MD5");
            digest.update(text.getBytes("UTF-8"));
            byte[] messageDigest = digest.digest();
            return asHex(messageDigest);
        } catch (Exception e){
            log.error(" -------- Exception :{}", e);
            return "";
        }
    }

    public static String asHex(byte[] buf){
        char[] chars = new char[2*buf.length];

        for(int i=0;i < buf.length;i++){
            chars[2 * i] = HEX_CHARS[(buf[i] & 240) >>> 4];
            chars[2 * i +1] = HEX_CHARS[buf[i] & 15];
        }

        return new String(chars);
    }

    public static String getMD5(String text,String key){
        String context;
        if(StringUtils.isEmpty(key)){
            context= text;
        } else {
            context= text.concat(key);
        }
        return DigestUtils.md5DigestAsHex(context.getBytes());
    }
}
