package com.gongshw.base.zuul;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

import com.gongshw.base.server.CommonServerApplication;

/**
 * Spring Cloud Zuul Proxy.
 *
 * @author gongshiwei@baidu.com
 */
@SpringBootApplication
@EnableZuulProxy
public class ZuulProxy {
    public static void main(String[] args) {
        CommonServerApplication.run(ZuulProxy.class, args);
    }
}
