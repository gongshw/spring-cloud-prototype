package com.gongshw.scp.service.demo1;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import com.gongshw.scp.base.server.CommonServerApplication;

/**
 * Demo1 Application.
 *
 * @author gongshiwei@baidu.com
 */
@SpringBootApplication
@EnableDiscoveryClient
public class Demo1Application {
    public static void main(String[] args) {
        CommonServerApplication.run(Demo1Application.class, args);
    }
}
