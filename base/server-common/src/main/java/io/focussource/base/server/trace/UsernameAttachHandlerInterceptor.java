package io.focussource.base.server.trace;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import brave.SpanCustomizer;
import io.focussource.base.server.security.SecurityHelper;

/**
 * A HandlerInterceptor to add Username to current brave Span.
 *
 * @author gongshw1992@gmail.com
 */
@Configuration
public class UsernameAttachHandlerInterceptor implements HandlerInterceptor, WebMvcConfigurer {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        SpanCustomizer span = (SpanCustomizer) request.getAttribute(SpanCustomizer.class.getName());
        String username = SecurityHelper.getUsername();
        if (span != null && StringUtils.isNotBlank(username)) {
            span.tag("username", username);
        }
        return true;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(this);
    }
}
