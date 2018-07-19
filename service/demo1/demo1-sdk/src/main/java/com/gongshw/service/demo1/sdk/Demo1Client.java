package com.gongshw.service.demo1.sdk;

import org.springframework.cloud.openfeign.FeignClient;

import com.gongshw.base.server.feign.FeignLoggingConfiguration;
import com.gongshw.service.demo1.api.Demo1Api;

/**
 * Demo1 Client.
 *
 * @author gongshiwei@baidu.com
 */
@FeignClient(value = "demo1", configuration = FeignLoggingConfiguration.class)
public interface Demo1Client extends Demo1Api {
}
