package io.focussource.base.server.feign;

import java.io.Reader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import feign.Response;
import feign.codec.ErrorDecoder;
import io.focussource.base.server.exception.ExceptionResponseBean;
import io.focussource.base.server.exception.ResponseException;
import lombok.extern.slf4j.Slf4j;

/**
 * ErrorDecoder for Feign Client.
 *
 * @author gongshw1992@gmail.com
 */
@Slf4j
@Component
public class FeignErrorDecoder implements ErrorDecoder {

    @Autowired
    ObjectMapper objectMapper;

    @Override
    public Exception decode(String methodKey, Response response) {
        try {
            Reader reader = response.body().asReader();
            ExceptionResponseBean bean = objectMapper.readValue(reader, ExceptionResponseBean.class);
            return new ResponseException(bean);
        } catch (Exception e) {
            log.error("unable to decode exception response: {}", methodKey, e);
            return e;
        }
    }
}
