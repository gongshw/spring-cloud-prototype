package io.focussource.service.demo1.api;

import java.time.Instant;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import io.focussource.service.demo1.api.model.Greeting;

/**
 * Demo1 Api.
 *
 * @author gongshw1992@gmail.com
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
