package io.focussource.base.admin;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import io.focussource.base.server.CommonApplication;
import io.focussource.base.server.CommonServiceApplication;

/**
 * Spring Boot Admin Web Dashboard Application.
 *
 * @author gongshw1992@gmail.com
 */
@CommonServiceApplication
@EnableAdminServer
public class AdminServer {
    public static void main(String[] args) {
        CommonApplication.run(AdminServer.class, args);
    }
}
