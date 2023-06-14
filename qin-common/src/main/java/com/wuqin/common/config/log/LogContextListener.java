package com.wuqin.common.config.log;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class LogContextListener implements ServletContextListener {
    public void contextInitialized(){

    }

    public void contextDestroyed(ServletContextEvent sce){
        String configPath = sce.getServletContext().getInitParameter("logConfigPath");
    }
}
