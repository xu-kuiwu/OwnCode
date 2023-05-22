package com.wuqin.application;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.MultipartAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.Environment;
import tk.mybatis.spring.annotation.MapperScan;

import java.net.InetAddress;
import java.net.UnknownHostException;

@Slf4j
@SpringBootApplication(scanBasePackages = {"com.wuqin"},exclude = {MultipartAutoConfiguration.class})
@MapperScan(basePackages = {"com.wuqin.*.dao"})
@ComponentScan("com.wuqin")
public class DataApplication extends SpringBootServletInitializer {

    public static void main(String[] args) throws UnknownHostException {
        ConfigurableApplicationContext application = SpringApplication.run(DataApplication.class,args);
        Environment env = application.getEnvironment();
        log.info("-----InetAddress.getLocalHost() :{}",InetAddress.getLocalHost());
        String ip = InetAddress.getLocalHost().getHostAddress();
        String port = env.getProperty("server.port");
        String path = env.getProperty("server.servlet.context-path");
        log.info("\n-----------------------------------------------\n\t" +
                "Application wuqin-ownCode is running! Acess URL:\n\t" +
                "Local: \thttp://localhost:" + port + path + "\n\t" +
                "External: \thttp://" + ip + ":" + port + path + "\n\t" +
                "Doc: \t\thttp://" + ip + ":" + port + path + "/swagger-ui.html\n\t" +
                "----------------------------------------------------");
    }

    @Override
    public SpringApplicationBuilder configure(SpringApplicationBuilder application){
        return application.sources(DataApplication.class);
    }
}
