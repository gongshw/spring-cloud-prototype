package io.focussource.base.server.exception;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.logging.LogLevel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;

import io.focussource.base.server.trace.TraceService;

/**
 * common server-side exception handler for spring mvc application.
 *
 * @author gongshw1992@gmail.com
 */
@ControllerAdvice
public class CommonExceptionHandler extends AbstractExceptionHandler {
    @Autowired
    public CommonExceptionHandler(TraceService traceService) {
        super(traceService);
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
