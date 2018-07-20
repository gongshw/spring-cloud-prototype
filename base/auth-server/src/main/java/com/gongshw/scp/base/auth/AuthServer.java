package com.gongshw.scp.base.auth;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;

import com.gongshw.scp.base.server.CommonServerApplication;

/**
 * Spring Cloud OAuth2 Server.
 *
 * @author gongshiwei@baidu.com
 */
@SpringBootApplication
@EnableAuthorizationServer
public class AuthServer {
    public static void main(String[] args) {
        CommonServerApplication.run(AuthServer.class, args);
    }
}
