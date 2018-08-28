package io.focussource.service.demo1.service;

import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.focussource.service.demo1.mapper.ModelMapper;
import lombok.extern.slf4j.Slf4j;

/**
 * demo1 service.
 */
@Slf4j
@Service
public class Demo1Service {
    private final ModelMapper modelMapper;

    @Autowired
    public Demo1Service(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public Instant time() {
        log.info("time");
        return modelMapper.mysqlTime().toInstant();
    }
}
