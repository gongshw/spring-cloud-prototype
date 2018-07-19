package com.gongshw.scp.base.server.exception;

import lombok.Data;

/**
 * Exception Data Class for RESTful Api.
 *
 * @author gongshiwei@baidu.com
 */
@Data
public class ExceptionResponseBean {
    String code;
    String message;
    String traceId;
    String spanId;
}
