package com.gongshw.scp.base.admin;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.gongshw.scp.base.server.CommonServerApplication;

import de.codecentric.boot.admin.server.config.EnableAdminServer;

/**
 * Spring Boot Admin Web Dashboard Application.
 *
 * @author gongshiwei@baidu.com
 */
@SpringBootApplication
@EnableAdminServer
public class AdminServer {
    public static void main(String[] args) {
        CommonServerApplication.run(AdminServer.class, args);
    }
}
