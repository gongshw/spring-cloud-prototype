package io.focussource.base.zuul;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.logging.LogLevel;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.netflix.zuul.exception.ZuulException;

import io.focussource.base.server.exception.AbstractExceptionHandler;
import io.focussource.base.server.exception.ExceptionResponseBean;
import io.focussource.base.server.trace.TraceService;

/**
 * Exception Controller for Zuul Filter.
 *
 * @author gongshw1992@gmail.com
 */
@Controller
public class ZuulExceptionController extends AbstractExceptionHandler implements ErrorController {
    @Value("${error.path:/error}")
    private String errorPath;

    @Autowired
    public ZuulExceptionController(TraceService traceService) {
        super(traceService);
    }

    @Override
    public String getErrorPath() {
        return errorPath;
    }

    @ResponseBody
    @RequestMapping(value = "${error.path:/error}")
    public ResponseEntity<ExceptionResponseBean> error(HttpServletRequest request) {
        final int status = getErrorStatus(request);
        final Throwable error = getError(request);
        return handler(error, status, LogLevel.ERROR);
    }

    private int getErrorStatus(HttpServletRequest request) {
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        return statusCode != null ? statusCode : HttpStatus.INTERNAL_SERVER_ERROR.value();
    }

    private Throwable getError(HttpServletRequest request) {
        return (Throwable) request.getAttribute("javax.servlet.error.exception");
    }

    @Override
    protected boolean isWrapperException(Throwable e) {
        return super.isWrapperException(e) || e instanceof ZuulException;
    }
}
