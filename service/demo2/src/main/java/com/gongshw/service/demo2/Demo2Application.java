package com.gongshw.service.demo2;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.openfeign.EnableFeignClients;

import com.gongshw.base.server.CommonServerApplication;

/**
 * Demo2 Application.
 *
 * @author gongshiwei@baidu.com
 */
@SpringBootApplication
@EnableCircuitBreaker
@EnableHystrixDashboard
@EnableFeignClients(basePackages = "com.gongshw.service")
public class Demo2Application {
    public static void main(String[] args) {
        CommonServerApplication.run(Demo2Application.class, args);
    }
}
