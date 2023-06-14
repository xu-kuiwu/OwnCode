package com.wuqin.common.config.log;

import ch.qos.logback.classic.spi.LoggerContextListener;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;

import javax.servlet.ServletContextListener;

public class LogConfig {
    public ServletListenerRegistrationBean<ServletContextListener> logContextListener() {
        ServletListenerRegistrationBean<ServletContextListener> result = new ServletListenerRegistrationBean<>();
        result.setListener(new LogContextListener());
        return result;
    }
}
