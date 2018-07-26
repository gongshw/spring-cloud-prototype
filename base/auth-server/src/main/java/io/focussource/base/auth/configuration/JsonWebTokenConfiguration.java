package io.focussource.base.auth.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

import io.focussource.base.server.security.BaseAuthProperties;
import io.focussource.base.server.security.CommonJwtTokenConverter;
import io.focussource.base.server.security.CommonJwtTokenDecoder;
import io.focussource.base.server.security.CommonJwtTokenEncoder;

/**
 * JWT Token Configuration.
 *
 * @author gongshw1992@gmail.com
 */
@Configuration
public class JsonWebTokenConfiguration {

    private final BaseAuthProperties baseAuthProperties;

    private final AuthServerProperties authServerProperties;

    @Autowired
    public JsonWebTokenConfiguration(BaseAuthProperties baseAuthProperties,
            AuthServerProperties authServerProperties) {
        this.baseAuthProperties = baseAuthProperties;
        this.authServerProperties = authServerProperties;
    }

    @Bean
    @Primary
    public org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter accessTokenDecoder() {
        String signingKey = baseAuthProperties.getSigningKey();
        String publicKey = baseAuthProperties.getPublicKey();
        String privateKey = authServerProperties.getPrivateKey();

        CommonJwtTokenEncoder jweEncoder = new CommonJwtTokenEncoder(privateKey, signingKey);
        CommonJwtTokenDecoder jweDecoder = new CommonJwtTokenDecoder(publicKey, signingKey);

        return new CommonJwtTokenConverter(jweEncoder, jweDecoder);
    }

    @Bean
    @Primary
    public JwtTokenStore tokenStore() {
        return new JwtTokenStore(accessTokenDecoder());
    }
}
