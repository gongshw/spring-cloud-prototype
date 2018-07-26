package io.focussource.base.server.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

/**
 * JWT Token Decoder Configuration.
 *
 * @author gongshw1992@gmail.com
 */
@Configuration
public class JsonWebTokeDecoderConfiguration {

    private final BaseAuthProperties baseAuthProperties;

    @Autowired
    public JsonWebTokeDecoderConfiguration(BaseAuthProperties baseAuthProperties) {
        this.baseAuthProperties = baseAuthProperties;
    }

    @Bean
    @ConditionalOnMissingBean
    public org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter accessTokenDecoder() {
        String signingKey = baseAuthProperties.getSigningKey();
        String publicKey = baseAuthProperties.getPublicKey();

        CommonJwtTokenDecoder jweDecoder = new CommonJwtTokenDecoder(publicKey, signingKey);

        return new CommonJwtTokenConverter(jweDecoder);
    }

    @Bean
    @ConditionalOnMissingBean
    public JwtTokenStore tokenStore() {
        return new JwtTokenStore(accessTokenDecoder());
    }
}
