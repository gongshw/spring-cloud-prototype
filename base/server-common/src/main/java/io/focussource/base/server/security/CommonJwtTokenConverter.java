package io.focussource.base.server.security;

import java.util.Map;

import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.exceptions.InvalidTokenException;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import io.focussource.base.server.json.JsonUtils;

/**
 * An enhanced JwtAccessTokenConverter support JWS Signature & JWE Encryption.
 *
 * @author gongshw1992@gmail.com
 */
public class CommonJwtTokenConverter extends JwtAccessTokenConverter {

    private final CommonJwtTokenEncoder encoder;
    private final CommonJwtTokenDecoder decoder;

    public CommonJwtTokenConverter(CommonJwtTokenEncoder encoder, CommonJwtTokenDecoder decoder) {
        this.encoder = encoder;
        this.decoder = decoder;
    }

    CommonJwtTokenConverter(CommonJwtTokenDecoder decoder) {
        // when used in a resource server, only a token decoder needed
        this(null, decoder);
    }

    @Override
    protected String encode(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
        if (encoder == null) {
            throw new IllegalStateException("Encoding token is not supported");
        }
        String payload;
        try {
            payload = JsonUtils.toJson(super.convertAccessToken(accessToken, authentication));
        } catch (Exception e) {
            throw new IllegalStateException("Cannot convert access token to JSON", e);
        }

        return encoder.encode(payload);
    }

    @Override
    protected Map<String, Object> decode(String token) {
        try {
            String payload = decoder.decode(token);
            Map<String, Object> claims = JsonUtils.parseToMap(payload);
            if (claims.containsKey(EXP) && claims.get(EXP) instanceof Integer) {
                Integer intValue = (Integer) claims.get(EXP);
                claims.put(EXP, new Long(intValue));
            }
            this.getJwtClaimsSetVerifier().verify(claims);
            return claims;
        } catch (Exception e) {
            throw new InvalidTokenException("Cannot convert access token to JSON", e);
        }
    }

    @Override
    public void afterPropertiesSet() {
        // Just Override this method and keep it empty.
        // The CommonJwtTokenConverter use this hook method to initialize a SignatureVerifier for decoding token.
        // However, CommonJwtTokenConverter get a CommonJwtTokenDecoder from the constructor.
    }
}
