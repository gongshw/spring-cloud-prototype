package io.focussource.service.demo1.controller;

import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RestController;

import io.focussource.service.demo1.api.Demo1Api;
import io.focussource.service.demo1.api.model.Greeting;
import io.focussource.service.demo1.service.Demo1Service;
import lombok.extern.slf4j.Slf4j;

/**
 * demo1 greeting controller.
 *
 * @author gongshw1992@gmail.com
 */
@Slf4j
@RestController
public class GreetingController implements Demo1Api {

    @Value("${greeting:hi}")
    private String greeting;

    private final Demo1Service demo1Service;

    @Autowired
    public GreetingController(Demo1Service demo1Service) {
        this.demo1Service = demo1Service;
    }

    @Override
    public Greeting hi() {
        log.info("hi");
        Greeting result = new Greeting();
        result.setMessage(greeting);
        return result;
    }

    @Override
    public Instant time() {
        return demo1Service.time();
    }

    @Override
    public void error() {
        throw new RuntimeException("i am error!!!");
    }
}
