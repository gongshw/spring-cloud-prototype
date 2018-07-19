package com.gongshw.scp.base.server.exception;

import lombok.Getter;

/**
 * Response Exception (for feign client).
 *
 * @author gongshiwei@baidu.com
 */
@Getter
public class ResponseException extends RuntimeException {

    private final String code;
    private final String traceId;
    private final String spanId;

    public ResponseException(ExceptionResponseBean responseBean) {
        super(responseBean.getMessage());
        code = responseBean.getCode();
        traceId = responseBean.getTraceId();
        spanId = responseBean.getSpanId();
    }
}
