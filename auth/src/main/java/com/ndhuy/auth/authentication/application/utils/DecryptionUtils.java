package com.ndhuy.auth.authentication.application.utils;

import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.interfaces.RSAPrivateCrtKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.RSAPublicKeySpec;

import com.ndhuy.auth.exception.domain.PublicKeyRetrievalException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DecryptionUtils {
    private DecryptionUtils() {
    }

    public static PublicKey loadPublicKeyWithPrivaKey(PrivateKey privateKey) {
        if (privateKey == null) {
            throw new PublicKeyRetrievalException("PrivateKey cannot be null");
        }
        if (!(privateKey instanceof RSAPrivateCrtKey)) {
            throw new PublicKeyRetrievalException("PrivateKey must be instance of RSAPrivateCrtKey");
        }
        return loadPublicKeyWithRSAPrivateCrtKey((RSAPrivateCrtKey) privateKey);
    }

    public static PublicKey loadPublicKeyWithRSAPrivateCrtKey(RSAPrivateCrtKey privateKey)
            throws PublicKeyRetrievalException {
        if (privateKey == null) {
            throw new PublicKeyRetrievalException("RSAPrivateCrtKey cannot be null");
        }
        try {
            KeyFactory kf = KeyFactory.getInstance(privateKey.getAlgorithm());

            RSAPublicKeySpec publicKeySpec = new RSAPublicKeySpec(privateKey.getModulus(),
                    privateKey.getPublicExponent());
            return kf.generatePublic(publicKeySpec);
        } catch (InvalidKeySpecException | NoSuchAlgorithmException e) {
            log.error("Error loading public key", e);
            throw new PublicKeyRetrievalException("Failed to load public key", e);
        }

    }

}
