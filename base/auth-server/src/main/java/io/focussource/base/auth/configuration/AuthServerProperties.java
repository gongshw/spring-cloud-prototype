package io.focussource.base.auth.configuration;

import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;

import io.focussource.base.auth.model.ClientCredential;
import io.focussource.base.auth.model.UserCredential;
import lombok.Data;

/**
 * Auth Server Properties.
 *
 * @author gongshw1992@gmail.com
 */
@Data
@RefreshScope
@Configuration
@ConfigurationProperties(prefix = "auth-server")
public class AuthServerProperties {
    private List<ClientCredential> clients;
    private List<UserCredential> users;
}
