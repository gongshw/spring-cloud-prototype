package com.gongshw.service.demo2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gongshw.service.demo1.api.model.Greeting;
import com.gongshw.service.demo2.service.FindService;

import reactor.core.publisher.Mono;

/**
 * Find Controller.
 *
 * @author gongshiwei@baidu.com
 */
@RestController
public class FindController {
    @Autowired
    private FindService findService;

    @GetMapping("/find")
    public Mono<Greeting> find() {
        return Mono.create(result -> {
            Greeting greeting = findService.find();
            result.success(greeting);
        });
    }

    @GetMapping("/error")
    public void error() {
        findService.error();
    }
}
