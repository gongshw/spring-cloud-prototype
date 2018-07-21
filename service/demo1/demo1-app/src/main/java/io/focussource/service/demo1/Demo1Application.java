package io.focussource.service.demo1;

import io.focussource.base.server.CommonApplication;
import io.focussource.base.server.CommonServiceApplication;

/**
 * Demo1 Application.
 *
 * @author gongshw1992@gmail.com
 */
@CommonServiceApplication
public class Demo1Application {
    public static void main(String[] args) {
        CommonApplication.run(Demo1Application.class, args);
    }
}
