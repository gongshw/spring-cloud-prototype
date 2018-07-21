package io.focussource.base.server.exception;

import lombok.Getter;

/**
 * Response Exception (for feign client).
 *
 * @author gongshw1992@gmail.com
 */
@Getter
public class ResponseException extends RuntimeException {

    private final String code;
    private final String traceId;
    private final String spanId;

    private ResponseException(String msg, String code, String traceId, String spanId) {
        super(msg);
        this.code = code;
        this.traceId = traceId;
        this.spanId = spanId;
    }

    public ResponseException(ExceptionResponseBean responseBean) {
        this(responseBean.getMessage(), responseBean.getCode(), responseBean.getTraceId(), responseBean.getSpanId());
    }
}
