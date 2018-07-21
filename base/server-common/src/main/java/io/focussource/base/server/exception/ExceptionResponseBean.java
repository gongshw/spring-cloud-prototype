package io.focussource.base.server.exception;

import lombok.Data;

/**
 * Exception Data Class for RESTful Api.
 *
 * @author gongshw1992@gmail.com
 */
@Data
public class ExceptionResponseBean {
    String code;
    String message;
    String traceId;
    String spanId;
}
