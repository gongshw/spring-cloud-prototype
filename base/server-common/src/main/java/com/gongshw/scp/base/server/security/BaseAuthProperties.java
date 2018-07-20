package com.gongshw.scp.base.server.security;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

/**
 * Base Auth Properties.
 *
 * @author gongshiwei@baidu.com
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "base.auth")
public class BaseAuthProperties {

    private boolean enabled = true;

    private String[] excludes = new String[0];

    private String signingKey = "signingKey";
}
