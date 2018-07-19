package com.gongshw.scp.base.server.feign;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import feign.Logger;

/**
 * Feign Logging Configuration.
 *
 * @author gongshiwei@baidu.com
 */
@Configuration
public class FeignLoggingConfiguration {

    @Value("${common.logging.level:FULL}")
    private Logger.Level loggerLevel = Logger.Level.FULL;

    @Bean
    Logger.Level feignLoggerLevel() {
        return loggerLevel;
    }
}
