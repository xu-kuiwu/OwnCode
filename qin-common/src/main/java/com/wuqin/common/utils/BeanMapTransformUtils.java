package com.wuqin.common.utils;

import cn.hutool.core.bean.BeanUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import com.wuqin.common.enums.ResponseEnum;
import com.wuqin.common.exception.AppException;
import com.wuqin.common.log.MonitorInfo;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.util.ClassUtils;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Slf4j
public class BeanMapTransformUtils {
    private static final Logger logger = LoggerFactory.getLogger(BeanMapTransformUtils.class);

    /**
     * 私有化构造方法
     */
    private BeanMapTransformUtils(){}

    /**
     *  将一个 JavaBean 对象转化为一个 Map
     *
     * @param bean 要转化的javaBean对象
     * @return
     */
    public static Map<String,Object> convertBean(Object bean){
        Map<String,Object> returnMap = Maps.newHashMap();
        try {
            Class<?> type= bean.getClass();
            BeanInfo beanInfo = Introspector.getBeanInfo(type);
            PropertyDescriptor[] propertyDescriptor = beanInfo.getPropertyDescriptors();
            for(int i=0;i < propertyDescriptor.length;i++){
                PropertyDescriptor descriptor = propertyDescriptor[i];
                String propertyName = descriptor.getName();
                if(!"class".equals(propertyName)){
                    Method readMethod = descriptor.getReadMethod();
                    Object result = readMethod.invoke(bean,new Object[0]);
                    if(result != null){
                        returnMap.put(propertyName,result);
                    }else if(!ClassUtils.isAssignable(Date.class,readMethod.getReturnType())){
                        returnMap.put(propertyName,"");
                    }
                }
            }
        }catch (Exception e){
            logger.error("bean转化Map异常，{}",e.getMessage());
            throw new AppException(ResponseEnum.SYSTEM_ERROR,e.getMessage(),e);
        }
        return returnMap;
    }

    /**
     * BeanUtils.copyProperties  对象赋值
     * source 对象不存在数据，需要赋值的对象
     * target 对象存在数据
     */
    public static void copyProperties(Object source,Object target){
        BeanUtils.copyProperties(source,target);
    }

    // BeanUtil
    // 对象转map
    public static void beanToMap(Object source){
        Map<String,Object> mapTest = BeanUtil.beanToMap(source);
    }

    public static void mapToBean(Map<String,Object> mapTest){
        MonitorInfo info = BeanUtil.mapToBean(mapTest,MonitorInfo.class,true);

    }

    //jsonObject
    public static void jsonObjectParseArray(String str){
        List<MonitorInfo> infoList = JSONObject.parseArray(str,MonitorInfo.class);
    }

    public static void jsonObjectObject(String str){
        MonitorInfo info = JSON.parseObject(str,MonitorInfo.class);
        Map<String,Object> infoMap = JSON.parseObject(str,Map.class);
    }

    public static void toJSON(Map<String,Object> mapTest){
        String str = JSON.toJSON(mapTest).toString();
    }


    //json
    public static void jsonParseArray(String str){
        List<MonitorInfo> infoList = JSON.parseArray(str,MonitorInfo.class);
    }

    public static void jsonParseObject(String str){
        MonitorInfo info = JSON.parseObject(str,MonitorInfo.class);
    }

    public static void toJSONString(Map<String,Object> mapTest){
        String str = JSON.toJSONString(mapTest);
    }
}
