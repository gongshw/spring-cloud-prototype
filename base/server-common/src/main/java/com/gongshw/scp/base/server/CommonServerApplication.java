package com.gongshw.scp.base.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

/**
 * Common Server Application Runner.
 *
 * @author gongshiwei@baidu.com
 */
@SpringBootApplication
@EnableResourceServer
@EnableDiscoveryClient
@EnableCircuitBreaker
@EnableHystrixDashboard
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
@EnableFeignClients(basePackages = "com.gongshw.scp.service")
public class CommonServerApplication {
    public static ConfigurableApplicationContext run(Class<?> context, String[] args) {
        Class<?>[] classes = new Class[] {context, CommonServerApplication.class};
        return SpringApplication.run(classes, args);
    }
}
