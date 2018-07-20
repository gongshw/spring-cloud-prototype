package com.gongshw.scp.base.server.trace;

import org.springframework.stereotype.Component;

import brave.Tracer;

/**
 * Trace Service based on Brave.
 *
 * @author gongshiwei@baidu.com
 */
@Component
public class TraceService {
    private final Tracer tracer;

    public TraceService(Tracer tracer) {
        this.tracer = tracer;
    }

    public String getTraceId() {
        return tracer.currentSpan().context().traceIdString();
    }

    public String getSpanId() {
        return Long.toHexString(tracer.currentSpan().context().spanId());
    }
}
