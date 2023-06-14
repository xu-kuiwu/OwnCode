package com.wuqin.admin.time;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 *
 * 运行非Web的Springboot项目时，会发现启动主类后马上就会停止，普通的Timer定时器无法达到定时自动执行Springboot项目的效果，
 * 下面我们用Springboot自带的注解（@Component、@Scheduled、@EnableScheduling）来进行定时任务。
 * @Component 加在类名上，代表这个类确保会被springboot扫描到
 * @Scheduled 加在方法名上来声明下面的方法是一个定时任务，注解的括号里包括 cron，fixDelay，fixRate 等类型
 * @EnableScheduling 加在启动类上，意思是 开启定时器任务
 *
 * @Component 代表这个类确保会被springboot扫描到
 * @Scheduled 来声明下面的方法是一个定时任务，注解的括号里包括 cron，fixDelay，fixRate 等类型
 * @Scheduled(fixedRate = 3000)里的fixedRate = 3000代表每3000毫秒执行一次，也就是3秒
 * 也可以写@Scheduled(cron="0/3 * * * * ? ") 意思也是每3秒执行一次，cron表达式大家可以百度一下
 *
 * 参考   https://blog.csdn.net/qq_48922459/article/details/121687993
 */
@Component
@Slf4j
public class ScheduledTest {

    @Scheduled(cron="00 00 * ? * * ")
    private void run(){
        log.info("这是一个定时任务测试类");
    }
}
