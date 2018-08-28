package io.focussource.base.server.json;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * JsonConfiguration.
 */
@Configuration
public class JsonConfiguration {
    @Primary
    @Bean
    public ObjectMapper objectMapper() {
        return JsonUtils.mapper();
    }
}
