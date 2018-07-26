package io.focussource.base.server.security;

import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.text.ParseException;

import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;
import org.springframework.security.oauth2.common.exceptions.InvalidTokenException;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWEDecrypter;
import com.nimbusds.jose.JWEObject;
import com.nimbusds.jose.JWSVerifier;
import com.nimbusds.jose.KeyLengthException;
import com.nimbusds.jose.Payload;
import com.nimbusds.jose.crypto.DirectDecrypter;
import com.nimbusds.jose.crypto.RSASSAVerifier;
import com.nimbusds.jwt.SignedJWT;

/**
 * Decoder for JWT Token. JWS&JWE supported.
 *
 * @author gongshw1992@gmail.com
 */
public class CommonJwtTokenDecoder {
    private final JWEDecrypter decrypter;
    private final JWSVerifier verifier;

    public CommonJwtTokenDecoder(String signingPublicKey, String encryptionKey) {
        byte[] decodedKey;
        try {
            decodedKey = Hex.decodeHex(encryptionKey);
        } catch (DecoderException e) {
            throw new IllegalArgumentException("Cannot decode key to bytes from hex: " + encryptionKey, e);
        }

        try {
            this.decrypter = new DirectDecrypter(new SecretKeySpec(decodedKey, 0, decodedKey.length, "AES"));
            this.verifier = new RSASSAVerifier(parsePublicKey(signingPublicKey));
        } catch (KeyLengthException e) {
            throw new IllegalArgumentException(e.getMessage(), e);
        }
    }

    String decode(String token) {

        // Parse into JWE object
        JWEObject jweObject;
        try {
            jweObject = JWEObject.parse(token);
        } catch (ParseException e) {
            throw new IllegalStateException("Cannot convert access token to JWE Object", e);
        }

        // Decrypt
        try {
            jweObject.decrypt(decrypter);
        } catch (JOSEException e) {
            throw new IllegalStateException("Cannot decrypt access token", e);
        }

        // Extract payload
        SignedJWT signedJwt = jweObject.getPayload().toSignedJWT();

        // Check the HMAC
        try {
            if (!signedJwt.verify(verifier)) {
                throw new InvalidTokenException("Invaild access token ");
            }
        } catch (JOSEException e) {
            throw new IllegalStateException("Cannot verify access token", e);
        }

        // Get the plain text
        Payload payload = signedJwt.getPayload();

        return payload.toString();
    }

    private RSAPublicKey parsePublicKey(String signingPublicKey) {
        X509EncodedKeySpec keySpecX509 = new X509EncodedKeySpec(Base64.decodeBase64(signingPublicKey));
        try {
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            return (RSAPublicKey) keyFactory.generatePublic(keySpecX509);
        } catch (InvalidKeySpecException | NoSuchAlgorithmException e) {
            throw new IllegalStateException("Cannot read public sign key", e);
        }
    }
}
