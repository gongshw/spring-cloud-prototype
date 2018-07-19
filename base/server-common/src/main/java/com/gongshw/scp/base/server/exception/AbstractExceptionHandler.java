package com.gongshw.scp.base.server.exception;

import org.springframework.boot.logging.LogLevel;
import org.springframework.http.ResponseEntity;

import brave.Tracer;
import lombok.extern.slf4j.Slf4j;

/**
 * general server-side exception logic.
 *
 * @author gongshiwei@baidu.com
 */
@Slf4j
public class AbstractExceptionHandler {
    private final Tracer tracer;

    public AbstractExceptionHandler(Tracer tracer) {
        this.tracer = tracer;
    }

    protected ResponseEntity<ExceptionResponseBean> handler(Throwable e, int status, LogLevel logLevel) {
        Throwable rootCause = getRootCause(e);
        String code = rootCause == null ? null : rootCause.getClass().getSimpleName();
        String message = rootCause == null ? null : rootCause.getMessage();
        logException(e, code, message, logLevel);
        ExceptionResponseBean responseBean = new ExceptionResponseBean();
        responseBean.setCode(code);
        responseBean.setMessage(message);
        responseBean.setTraceId(tracer.currentSpan().context().traceIdString());
        responseBean.setSpanId(tracer.currentSpan().context().traceIdString());
        return ResponseEntity.status(status).body(responseBean);
    }

    private void logException(Throwable e, String code, String message, LogLevel logLevel) {
        if (logLevel == LogLevel.ERROR) {
            log.error("[{}] {}", code, message, e);
        } else if (logLevel == LogLevel.WARN) {
            log.warn("[{}] {}", code, message, e);
        } else if (logLevel == LogLevel.INFO) {
            log.info("[{}] {}", code, message);
        }
    }

    private Throwable getRootCause(Throwable e) {
        if (e == null || e.getCause() == null || e.getCause().getMessage() == null) {
            return e;
        }
        if (isWrapperException(e)) {
            return getRootCause(e.getCause());
        }
        return e;
    }

    protected boolean isWrapperException(Throwable e) {
        return e instanceof RuntimeException;
    }
}
