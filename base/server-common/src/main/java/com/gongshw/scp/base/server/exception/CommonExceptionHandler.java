package com.gongshw.scp.base.server.exception;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.logging.LogLevel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;

import brave.Tracer;

/**
 * common server-side exception hanlder for spring mvc application.
 *
 * @author gongshiwei@baidu.com
 */
@ControllerAdvice
public class CommonExceptionHandler extends AbstractExceptionHandler {
    @Autowired
    public CommonExceptionHandler(Tracer tracer) {
        super(tracer);
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<ExceptionResponseBean> handler404(Exception e) {
        return handler(e, HttpStatus.NOT_FOUND.value(), LogLevel.INFO);
    }

    @ExceptionHandler
    public ResponseEntity<ExceptionResponseBean> handler(Exception e) {
        return handler(e, HttpStatus.INTERNAL_SERVER_ERROR.value(), LogLevel.ERROR);
    }
}
