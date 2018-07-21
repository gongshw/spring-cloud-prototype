package io.focussource.base.zuul;

import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

import io.focussource.base.server.CommonApplication;
import io.focussource.base.server.CommonServiceApplication;

/**
 * Spring Cloud Zuul Proxy.
 *
 * @author gongshw1992@gmail.com
 */
@CommonServiceApplication
@EnableZuulProxy
public class ZuulProxy {
    public static void main(String[] args) {
        CommonApplication.run(ZuulProxy.class, args);
    }
}
