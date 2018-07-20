package com.gongshw.scp.base.auth.configuration;

import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import com.gongshw.scp.base.auth.model.ClientCredential;
import com.gongshw.scp.base.auth.model.UserCredential;

import lombok.Data;

/**
 * Auth Server Properties.
 *
 * @author gongshiwei@baidu.com
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "auth-server")
public class AuthServerProperties {
    private List<ClientCredential> clients;
    private List<UserCredential> users;
}
