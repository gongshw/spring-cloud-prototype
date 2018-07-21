package io.focussource.service.demo1.controller;

import java.time.Instant;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RestController;

import io.focussource.service.demo1.api.Demo1Api;
import io.focussource.service.demo1.api.model.Greeting;
import lombok.extern.slf4j.Slf4j;

/**
 * demo1 greeing controller.
 *
 * @author gongshw1992@gmail.com
 */
@Slf4j
@RestController
public class GreetingController implements Demo1Api {

    @Value("${greeting}")
    private String greeting;

    @Override
    public Greeting hi() {
        log.info("hi");
        Greeting result = new Greeting();
        result.setMessage(greeting);
        return result;
    }

    @Override
    public Instant time() {
        log.info("time");
        return Instant.now();
    }

    @Override
    public void error() {
        throw new RuntimeException("i am error!!!");
    }
}
