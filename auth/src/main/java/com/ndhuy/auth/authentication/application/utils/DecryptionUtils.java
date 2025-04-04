package com.ndhuy.auth.authentication.application.utils;

import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.interfaces.RSAPrivateCrtKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.RSAPublicKeySpec;

import com.ndhuy.app.exception.application.runtime.NotFoundRuntimeException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DecryptionUtils {
    private DecryptionUtils() {
    }

    /**
     * @param privateKey
     * @return PublicKey
     */
    public static PublicKey loadPublicKeyWithPrivaKey(PrivateKey privateKey) {
        if (privateKey == null) {
            throw new NotFoundRuntimeException("private_key.not_null");
        }
        if (!(privateKey instanceof RSAPrivateCrtKey)) {
            throw new NotFoundRuntimeException("private_key.instance");
        }
        return loadPublicKeyWithRSAPrivateCrtKey((RSAPrivateCrtKey) privateKey);
    }

    /**
     * @param privateKey
     * @return PublicKey
     * @throws PublicKeyRetrievalException
     */
    public static PublicKey loadPublicKeyWithRSAPrivateCrtKey(RSAPrivateCrtKey privateKey) {
        if (privateKey == null) {
            throw new NotFoundRuntimeException("private_key.not_null");
        }
        try {
            KeyFactory kf = KeyFactory.getInstance(privateKey.getAlgorithm());

            RSAPublicKeySpec publicKeySpec = new RSAPublicKeySpec(privateKey.getModulus(),
                    privateKey.getPublicExponent());
            return kf.generatePublic(publicKeySpec);
        } catch (InvalidKeySpecException | NoSuchAlgorithmException e) {
            log.error("Error loading public key", e);
            throw new NotFoundRuntimeException("public_key.loading");
        }

    }

}
