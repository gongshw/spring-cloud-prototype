package io.focussource.base.server.trace;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import brave.Tracer;

/**
 * Trace Service based on Brave.
 *
 * @author gongshw1992@gmail.com
 */
@Component
public class TraceService {
    private final Tracer tracer;

    public TraceService(@Autowired(required = false) Tracer tracer) {
        this.tracer = tracer;
    }

    public String getTraceId() {
        if (tracer == null) {
            return "";
        }
        return tracer.currentSpan().context().traceIdString();
    }

    public String getSpanId() {
        if (tracer == null) {
            return "";
        }
        return Long.toHexString(tracer.currentSpan().context().spanId());
    }
}
