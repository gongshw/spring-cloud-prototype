package io.focussource.base.auth.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

import lombok.val;

/**
 * Security Config for OAuth2 Server.
 *
 * @author gongshw1992@gmail.com
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final PasswordEncoder passwordEncoder;

    private final AuthServerProperties authServerProperties;

    @Autowired
    public SecurityConfig(PasswordEncoder passwordEncoder,
            AuthServerProperties authServerProperties) {
        this.passwordEncoder = passwordEncoder;
        this.authServerProperties = authServerProperties;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        val authentication = auth.inMemoryAuthentication();
        authServerProperties.getUsers().forEach(credential -> authentication
                .withUser(credential.getUsername())
                .password(passwordEncoder.encode(credential.getPassword()))
                .roles(credential.getRoles().toArray(new String[0])));
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
