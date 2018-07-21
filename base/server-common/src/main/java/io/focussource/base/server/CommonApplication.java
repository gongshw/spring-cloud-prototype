package io.focussource.base.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * Common Server Application Runner.
 *
 * @author gongshw1992@gmail.com
 */
@SpringBootApplication
public class CommonApplication {
    public static ConfigurableApplicationContext run(Class<?> context, String[] args) {
        Class<?>[] classes = new Class[] {context, CommonApplication.class};
        return SpringApplication.run(classes, args);
    }
}
