package io.focussource.base.server.security;

import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.text.ParseException;

import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;

import com.nimbusds.jose.EncryptionMethod;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWEAlgorithm;
import com.nimbusds.jose.JWEEncrypter;
import com.nimbusds.jose.JWEHeader;
import com.nimbusds.jose.JWEObject;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSSigner;
import com.nimbusds.jose.KeyLengthException;
import com.nimbusds.jose.Payload;
import com.nimbusds.jose.crypto.DirectEncrypter;
import com.nimbusds.jose.crypto.RSASSASigner;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;

/**
 * Encoder for JWT Token. JWS&JWE supported.
 *
 * @author gongshw1992@gmail.com
 */
public class CommonJwtTokenEncoder {
    private final JWEEncrypter encrypter;
    private final JWSSigner signer;

    public CommonJwtTokenEncoder(String privateSigningKey, String encryptionKey) {
        byte[] decodedKey;
        try {
            decodedKey = Hex.decodeHex(encryptionKey);
        } catch (DecoderException e) {
            throw new IllegalArgumentException("Cannot decode key to bytes from hex: " + encryptionKey, e);
        }

        try {
            this.encrypter = new DirectEncrypter(new SecretKeySpec(decodedKey, 0, decodedKey.length, "AES"));
            this.signer = new RSASSASigner(parsePrivateKey(privateSigningKey));
        } catch (KeyLengthException e) {
            throw new IllegalArgumentException(e.getMessage(), e);
        }
    }

    String encode(String content) {

        JWTClaimsSet claimsSet;
        try {
            claimsSet = JWTClaimsSet.parse(content);
        } catch (ParseException e) {
            throw new IllegalStateException("Cannot parse access token", e);
        }

        // Set the plain text
        SignedJWT signedJwt = new SignedJWT(new JWSHeader(JWSAlgorithm.RS256), claimsSet);

        // Sign payload
        try {
            signedJwt.sign(signer);
        } catch (JOSEException e) {
            throw new IllegalStateException("Cannot signer access token", e);
        }

        // Create the jwe header
        JWEHeader header = new JWEHeader(JWEAlgorithm.DIR, EncryptionMethod.A128GCM);

        // Create the JWE object and encrypt it
        JWEObject jweObject = new JWEObject(header, new Payload(signedJwt));

        try {
            jweObject.encrypt(encrypter);
            // Serialise to compact JOSE form
            return jweObject.serialize();
        } catch (JOSEException e) {
            throw new IllegalStateException("Cannot encrypt access token", e);
        }
    }

    private PrivateKey parsePrivateKey(String privateSignKey) {
        // extract the private key
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(Base64.decodeBase64(privateSignKey));
        try {
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            return keyFactory.generatePrivate(keySpec);
        } catch (InvalidKeySpecException | NoSuchAlgorithmException e) {
            throw new IllegalStateException("Cannot read private sign key", e);
        }
    }
}
