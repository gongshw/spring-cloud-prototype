package com.gongshw.scp.service.demo1.sdk;

import java.time.Instant;
import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gongshw.scp.base.server.feign.FeignConfiguration;
import com.gongshw.scp.service.demo1.api.model.Greeting;

/**
 * Demo1 Client.
 *
 * @author gongshiwei@baidu.com
 */
@FeignClient(value = "demo1", configuration = FeignConfiguration.class)
@RequestMapping("/v1")
public interface Demo1Client {

    @GetMapping("/greeting")
    Greeting hi();

    @GetMapping("/time")
    Instant time();

    @GetMapping("/error")
    void error();

    @GetMapping("/security/name")
    String name();

    @GetMapping("/security/roles")
    List<String> roles();

    @GetMapping("/security/checkAdmin")
    String checkAdmin();
}
