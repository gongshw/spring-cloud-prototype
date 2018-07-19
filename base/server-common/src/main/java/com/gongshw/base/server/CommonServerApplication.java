package com.gongshw.base.server;

import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Common Server Application Runner.
 *
 * @author gongshiwei@baidu.com
 */
@Configuration
@ComponentScan
public class CommonServerApplication {
    public static ConfigurableApplicationContext run(Class<?> context, String[] args) {
        Class<?>[] classes = new Class[] {context, CommonServerApplication.class};
        return SpringApplication.run(classes, args);
    }
}
