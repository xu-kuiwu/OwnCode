package com.wuqin.aspect;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.wuqin.common.constants.CommonConstants;
import com.wuqin.common.log.MonitorInfo;
import com.wuqin.common.log.MonitorLog;
import com.wuqin.common.utils.DateUtil;
import com.wuqin.dto.LogInfo;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import java.lang.reflect.Method;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Aspect
@Component
@Slf4j
public class LogAspect {
    private static AtomicInteger threadId = new AtomicInteger(1);

    @Pointcut("execution(public * com.wuqin.controller.*.*(..))")
    public void log(){}

    /**
     * aspect 相关用法
     *
     * 注解作用
     * @Aspect 把当前类标识为一个切面
     * @Pointcut
     * Pointcut是织入Advice的触发条件。每个Pointcut的定义包括2部分，一是表达式，二是方法签名。
     * 方法签名必须是public及void型。可以将Pointcut中的方法看作是一个被Advice引用的助记符，因为表达式不直观，
     * 因此我们可以通过方法签名的方式为此表达式命名。
     * 因此Pointcut中的方法只需要方法签名，而不需要在方法体内编写实际代码。
     *
     * @Around 环绕增强，目标方法执行前后分别执行一些代码
     * @AfterReturning 返回增强，目标方法正常执行完毕时执行
     * @Before 前置增强，目标方法执行之前执行
     * @AfterThrowing 异常抛出增强，目标方法发生异常的时候执行
     * @After 后置增强，不管是抛出异常或者正常退出都会执行
     *
     *
     */

    //⽬标⽅法执⾏前后分别执⾏⼀些代码
    @Around("log()")
    public Object watchControllerLog(ProceedingJoinPoint jp) throws Throwable{
        int i = threadId.get();
        if(i >= 100000){
            threadId.compareAndSet(i,0);
        }
        Thread.currentThread().setName("thread-" + threadId.incrementAndGet());
        //获取拦截切入点位置
        final Signature sig= jp.getSignature();
        if(!(sig instanceof MethodSignature)){
            throw new IllegalArgumentException("该注解只能放在方法上");
        }
        final MethodSignature msig=(MethodSignature) sig;

        //获取拦截的方法
        final Method method = msig.getMethod();

        final LogInfo logInfo = new LogInfo();
        Object target = jp.getTarget();
        logInfo.setClsName(target.getClass().getSimpleName());
        logInfo.setMethod(method.getName());
        //获取请求参数
        logInfo.setReqParam(this.getRequestParam(jp));

        logInfo.setGmtCreate(DateUtil.dateToStr(new Date(),DateUtil.DATETIME_FORMAT_S));

        //入口打印
        this.printIn(logInfo);
        final StopWatch clock = new StopWatch();
        Object result = null;
        clock.start();
        try {
            //发生异常会抛出
            result = jp.proceed();
        }catch (Exception e){
            log.error("exception:{}",e);
        }
        clock.stop();
        logInfo.setActionTime(clock.getTotalTimeMillis());

        //打印出口
        this.printOut(logInfo,result);
        return result;
    }

    private void printIn(LogInfo logInfo){
        logInfo.begin();
        log.info("------------------- start service -----------------------");
        log.info("api = {}, method = {}, startTime = {}, params = {}",logInfo.getClsName(),logInfo.getMethod(),logInfo.getGmtCreate(),logInfo.getReqParam());
    }

    private void printOut(LogInfo logInfo,Object result){
        log.info("api = {}, method = {}, startTime = {}, actionTime = {},result = {}",logInfo.getClsName(),logInfo.getMethod(),
                logInfo.getGmtCreate(),logInfo.getActionTime(),result);
        log.info("------------------- invoke end -----------------------");
        MonitorLog.log(transferLog2Monitor(logInfo),result);
        logInfo.end();
    }

    private String getRequestParam(ProceedingJoinPoint jp){
        try {
            final List<String> paramList = Lists.newArrayList();
            final Object[] args = jp.getArgs();
            for(final Object arg:args){
                final String paramStr = JSON.toJSONString(arg);
                if(paramStr != null){
                    paramList.add(paramStr);
                }
            }
            return paramList.toString();
        }catch (final Exception e){
            log.error("request param error {}",e);
        }
        return "";
    }

    /**
     * 转换
     * @param logInfo
     * @return
     */
    private MonitorInfo transferLog2Monitor(LogInfo logInfo){
        MonitorInfo monitorInfo = new MonitorInfo();
        monitorInfo.setSystem(CommonConstants.OWNCODE);
        monitorInfo.setTime(logInfo.getActionTime());
        monitorInfo.setMethod(logInfo.getClsName() + CommonConstants.COMMA + logInfo.getMethod());
        return monitorInfo;
    }
}
