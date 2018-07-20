package com.gongshw.scp.base.server.feign;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.google.common.net.HttpHeaders;

import feign.Logger;
import feign.RequestInterceptor;
import feign.RequestTemplate;

/**
 * Feign Logging Configuration.
 *
 * @author gongshiwei@baidu.com
 */
@Configuration
public class FeignConfiguration {

    @Value("${base.logging.level:FULL}")
    private Logger.Level loggerLevel = Logger.Level.FULL;

    @Bean
    Logger.Level feignLoggerLevel() {
        return loggerLevel;
    }

    @Bean
    public RequestInterceptor oauth2FeignRequestInterceptor() {
        return this::apply;
    }

    private void apply(RequestTemplate template) {
        HttpServletRequest request = getRequest();
        if (request == null) {
            return;
        }
        String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (authHeader == null) {
            return;
        }
        template.header(HttpHeaders.AUTHORIZATION, authHeader);
    }

    private HttpServletRequest getRequest() {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        if (requestAttributes == null) {
            return null;
        }
        if (requestAttributes instanceof ServletRequestAttributes) {
            return ((ServletRequestAttributes) requestAttributes).getRequest();
        } else {
            return null;
        }
    }
}
