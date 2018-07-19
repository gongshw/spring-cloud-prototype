package com.gongshw.scp.service.demo1.api;

import java.time.Instant;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gongshw.scp.service.demo1.api.model.Greeting;

/**
 * Demo1 Api.
 *
 * @author gongshiwei@baidu.com
 */
@RequestMapping("/v1")
public interface Demo1Api {
    @GetMapping("/greeting")
    Greeting hi();

    @GetMapping("/time")
    Instant time();

    @GetMapping("/error")
    void error();
}
