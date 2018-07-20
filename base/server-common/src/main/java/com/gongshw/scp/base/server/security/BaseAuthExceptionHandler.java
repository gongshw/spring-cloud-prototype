package com.gongshw.scp.base.server.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.boot.logging.LogLevel;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.provider.error.DefaultOAuth2ExceptionRenderer;
import org.springframework.security.oauth2.provider.error.OAuth2ExceptionRenderer;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

import com.gongshw.scp.base.server.exception.AbstractExceptionHandler;
import com.gongshw.scp.base.server.trace.TraceService;

import lombok.val;

/**
 * ExceptionHandler for OAuth2.
 *
 * @author gongshiwei@baidu.com
 */
@Component
public class BaseAuthExceptionHandler extends AbstractExceptionHandler
        implements AuthenticationEntryPoint, AccessDeniedHandler {

    private OAuth2ExceptionRenderer exceptionRenderer = new DefaultOAuth2ExceptionRenderer();

    public BaseAuthExceptionHandler(TraceService traceService) {
        super(traceService);
    }

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
            AuthenticationException authException) {
        writeException(request, response, authException);
    }

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response,
            AccessDeniedException accessDeniedException) {
        writeException(request, response, accessDeniedException);

    }

    private void writeException(HttpServletRequest request, HttpServletResponse response, Exception authException) {
        val entity = super.handler(authException, HttpStatus.FORBIDDEN.value(), LogLevel.WARN);
        try {
            exceptionRenderer.handleHttpEntityResponse(entity, new ServletWebRequest(request, response));
            response.flushBuffer();
        } catch (Exception ex) {
            ExceptionUtils.rethrow(ex);
        }
    }
}
