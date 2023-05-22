package com.wuqin.dto;

import com.wuqin.common.constants.CommonConstants;
import com.wuqin.common.utils.StringUtil;
import lombok.Data;
import org.slf4j.MDC;

@Data
public class LogInfo {
    private String clsName;
    /**
     * 执行的方法
     */
    private String method;
    /**
     * 执行的时间
     */
    private String gmtCreate;
    /**
     *
     */
    private long actionTime;
    /**
     * 请求参数
     */
    private String reqParam;
    /**
     *
     */
    private String invokeNo;

    public void begin(){
        if(null == this.invokeNo){
            MDC.put(CommonConstants.STR_INVOKE_NO, StringUtil.getUUID());
        }
    }

    public void end(){
        if(null != this.invokeNo){
            MDC.remove(CommonConstants.STR_INVOKE_NO);
        }
        MDC.clear();
    }
}
