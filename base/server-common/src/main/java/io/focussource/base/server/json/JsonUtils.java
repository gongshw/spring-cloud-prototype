package io.focussource.base.server.json;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Map;
import java.util.TimeZone;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import lombok.experimental.UtilityClass;

/**
 * Json parser util.
 *
 * @author gongshw1992@gmail.com
 */
@UtilityClass
public class JsonUtils {
    private static final ObjectMapper MAPPER = new ObjectMapper();

    static {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        MAPPER.setDateFormat(dateFormat);
        MAPPER.setTimeZone(TimeZone.getTimeZone("UTC"));
        MAPPER.registerModule(new JavaTimeModule());
    }

    static ObjectMapper mapper() {
        return MAPPER;
    }

    public static String toJson(Object obj) {
        try {
            return MAPPER.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException("Cannot format json", e);
        }
    }

    public static Map<String, Object> parseToMap(String json) {
        try {
            return MAPPER.readValue(json, new TypeReference<Map<String, Object>>() {
            });
        } catch (Exception e) {
            throw new IllegalArgumentException("Cannot parse json", e);
        }
    }
}
