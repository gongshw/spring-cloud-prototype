package com.gongshw.scp.base.server.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

/**
 * Configuration for ResourceServer.
 *
 * @author gongshiwei@baidu.com
 */
@Configuration
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

    private final BaseAuthProperties baseAuthProperties;

    private final BaseAuthExceptionHandler baseAuthExceptionHandler;

    @Autowired
    public ResourceServerConfiguration(
            BaseAuthProperties baseAuthProperties,
            BaseAuthExceptionHandler baseAuthExceptionHandler) {
        this.baseAuthProperties = baseAuthProperties;
        this.baseAuthExceptionHandler = baseAuthExceptionHandler;
    }

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) {
        resources.tokenStore(new JwtTokenStore(accessTokenConverter()));
        resources.accessDeniedHandler(baseAuthExceptionHandler);
        resources.authenticationEntryPoint(baseAuthExceptionHandler);
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        if (baseAuthProperties.isEnabled()) {
            http.authorizeRequests()
                    .antMatchers(HttpMethod.GET, "/hystrix").permitAll()
                    .antMatchers(HttpMethod.GET, "/hystrix/**").permitAll()
                    .antMatchers(HttpMethod.GET, "/actuator/**").permitAll()
                    .antMatchers(baseAuthProperties.getExcludes()).permitAll()
                    .anyRequest().authenticated()
                    .and()
                    .exceptionHandling().accessDeniedHandler(baseAuthExceptionHandler);

        } else {
            http.authorizeRequests().anyRequest().permitAll();
        }
    }

    @Bean
    public JwtAccessTokenConverter accessTokenConverter() {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setSigningKey("signingKey");
        return converter;
    }
}
