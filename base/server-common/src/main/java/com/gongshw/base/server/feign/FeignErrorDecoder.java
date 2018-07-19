package com.gongshw.base.server.feign;

import java.io.Reader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gongshw.base.server.exception.ExceptionResponseBean;
import com.gongshw.base.server.exception.ResponseException;

import feign.Response;
import feign.codec.ErrorDecoder;
import lombok.extern.slf4j.Slf4j;

/**
 * ErrorDecoder for Feign Client.
 *
 * @author gongshiwei@baidu.com
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
