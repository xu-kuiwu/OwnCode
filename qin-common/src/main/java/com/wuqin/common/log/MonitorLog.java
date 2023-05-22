package com.wuqin.common.log;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import com.wuqin.common.utils.BeanMapTransformUtils;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.util.Map;
import java.util.Objects;

@Slf4j
public class MonitorLog {
    private static final Logger monitorLog = LoggerFactory.getLogger(MonitorLog.class);

    public static void log(MonitorInfo monitorInfo,Object result){
        try {
            fixCode(result,monitorInfo);
        }catch (Exception e){
            log.error("fix cod err :{}",e.getMessage());
        }
        monitorLog.info("prefix=" + monitorInfo.getSystem() + JSONObject.toJSONString(monitorInfo));
    }

    /**
     * 返回code组装
     * @param result
     * @param monitorInfo
     */
    private static void fixCode(Object result,MonitorInfo monitorInfo){
        if(result instanceof Map){
            Map<String,Object> map= Maps.newHashMap();
            String code= Objects.toString(map.get("responseCode"),"");
            if(!StringUtils.isEmpty(code)){
                monitorInfo.setCode(code);
                monitorInfo.setMsg(Objects.toString(map.get("responseMsg"),""));
                return;
            }
            code = Objects.toString(map.get("code"),"");
            if(!StringUtils.isEmpty(code)){
                monitorInfo.setCode(code);
                monitorInfo.setMsg(Objects.toString(map.get("msg"),""));
                return;
            }
            code = Objects.toString(map.get("returnCode"),"");
            if(!StringUtils.isEmpty(code)){
                monitorInfo.setCode(code);
                monitorInfo.setMsg(Objects.toString(map.get("returnMsg"),""));
                return;
            }
        }else {
            fixCode(BeanMapTransformUtils.convertBean(result),monitorInfo);
        }
    }
}