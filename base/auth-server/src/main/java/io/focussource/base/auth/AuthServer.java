package io.focussource.base.auth;

import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;

import io.focussource.base.server.CommonApplication;
import io.focussource.base.server.CommonServiceApplication;

/**
 * Spring Cloud OAuth2 Server.
 *
 * @author gongshw1992@gmail.com
 */
@CommonServiceApplication
@EnableAuthorizationServer
public class AuthServer {
    public static void main(String[] args) {
        CommonApplication.run(AuthServer.class, args);
    }
}
