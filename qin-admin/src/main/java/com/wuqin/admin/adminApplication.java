package com.wuqin.admin;

import com.wuqin.common.config.log.EnableLog;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.MultipartAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.Environment;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import tk.mybatis.spring.annotation.MapperScan;

import java.net.InetAddress;
import java.net.UnknownHostException;

@Slf4j
@SpringBootApplication(scanBasePackages = {"com.wuqin"}, exclude = {MultipartAutoConfiguration.class})
@MapperScan(basePackages = {"com.wuqin.*.dao"})
@ComponentScan("com.wuqin")
@EnableSwagger2
//@EnableLog
public class adminApplication extends SpringBootServletInitializer {
    @Override
    public SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(adminApplication.class);
    }

    public static void main(String[] args) throws UnknownHostException {
        ConfigurableApplicationContext application = SpringApplication.run(adminApplication.class, args);
        Environment env = application.getEnvironment();
        log.info("-----InetAddress.getLocalHost() :{}", InetAddress.getLocalHost());
        String ip = InetAddress.getLocalHost().getHostAddress();
        String port = env.getProperty("server.port");
        String path = env.getProperty("server.servlet.context-path");
        log.info("\n-----------------------------------------------\n\t" +
                "\n------------------admin-----------------------------\n\t" +
                "Application wuqin-educationSystem is running! Acess URL:\n\t" +
                "Local: \thttp://localhost:" + port + path + "\n\t" +
                "External: \thttp://" + ip + ":" + port + path + "\n\t" +
                "Doc: \t\thttp://" + ip + ":" + port + path + "/swagger-ui.html\n\t" +
                "----------------------------------------------------");
    }

}
