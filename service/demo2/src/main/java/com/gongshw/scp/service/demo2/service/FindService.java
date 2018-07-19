package com.gongshw.scp.service.demo2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gongshw.scp.service.demo1.api.model.Greeting;
import com.gongshw.scp.service.demo1.sdk.Demo1Client;

/**
 * Find Service.
 *
 * @author gongshiwei@baidu.com
 */
@Service
public class FindService {
    @Autowired
    Demo1Client demo1Client;

    public Greeting find() {
        Greeting greeting = new Greeting();
        greeting.setMessage(demo1Client.hi().getMessage() + " " + demo1Client.time());
        return greeting;
    }

    public void error() {
        demo1Client.error();
    }
}
