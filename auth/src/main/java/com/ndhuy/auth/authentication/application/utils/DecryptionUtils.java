package com.ndhuy.auth.authentication.application.utils;

import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.interfaces.RSAPrivateCrtKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.RSAPublicKeySpec;

public class DecryptionUtils {
    private DecryptionUtils() {

    }

    public static PublicKey loadPublicKeyWithPrivaKey(PrivateKey privateKey) {
        return loadPublicKeyWithRSAPrivateCrtKey((RSAPrivateCrtKey) privateKey);
    }

    public static PublicKey loadPublicKeyWithRSAPrivateCrtKey(RSAPrivateCrtKey privateKey) {

        try {
            KeyFactory kf = KeyFactory.getInstance(privateKey.getAlgorithm());

            RSAPublicKeySpec publicKeySpec = new RSAPublicKeySpec(privateKey.getModulus(),
                    privateKey.getPublicExponent());
            return kf.generatePublic(publicKeySpec);
        } catch (InvalidKeySpecException | NoSuchAlgorithmException e) {

            e.printStackTrace();
        }
        return null;
    }
}
