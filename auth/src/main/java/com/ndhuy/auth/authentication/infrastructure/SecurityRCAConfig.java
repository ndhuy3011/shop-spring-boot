package com.ndhuy.auth.authentication.infrastructure;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.Objects;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.vault.core.VaultTemplate;
import org.springframework.vault.support.VaultResponse;

import com.ndhuy.auth.authentication.application.utils.EncryptionUtils;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
public class SecurityRCAConfig {
    private static final String PKCS_1_PEM_HEADER = "-----BEGIN RSA PRIVATE KEY-----";
    private static final String PKCS_1_PEM_FOOTER = "-----END RSA PRIVATE KEY-----";
    private static final String PKCS_8_PEM_HEADER = "-----BEGIN PRIVATE KEY-----";
    private static final String PKCS_8_PEM_FOOTER = "-----END PRIVATE KEY-----";
    private final VaultTemplate vaultTemplate;

    private RSAPublicKey publicKeyRca;
    private RSAPrivateKey privateKeyRca;

    @Bean
    RSAPublicKey publicKeyRca() {
        return this.publicKeyRca;
    }

    @Bean
    RSAPrivateKey privateKey() {
        return this.privateKeyRca;
    }

    @PostConstruct
    public void init() throws InvalidKeySpecException, NoSuchAlgorithmException, UnsupportedEncodingException,
            NoSuchProviderException {
        log.info("Loading Vault Key");
        KeyFactory kf = KeyFactory.getInstance("RSA");

        VaultResponse response = vaultTemplate.read("authentication-client/authentication");
        Objects.requireNonNull(response, "Vault Null");
        var data = response.getData();
        Objects.requireNonNull(data, "Data Vault NULL");
        log.info("Loading Private key");
        // Load private key
        String privateKeyBase64 = data.get("private_key").toString();
        var privateKey = EncryptionUtils.loadKeyBase64(privateKeyBase64);
        this.privateKeyRca = (RSAPrivateKey) privateKey;

        // Load public key
        String publicKeyBase64 = data.get("public_key").toString();
        log.info("Public Key Base64: " + publicKeyBase64);
        try {
            byte[] decodedPublicKey = Base64.getDecoder().decode(publicKeyBase64);
            log.info("Public Key Bytes Length: " + decodedPublicKey.length);
            X509EncodedKeySpec specPublicKey = new X509EncodedKeySpec(decodedPublicKey);
            this.publicKeyRca = (RSAPublicKey) kf.generatePublic(specPublicKey);
            log.info("Public Key Loaded: " + publicKeyRca);
        } catch (IllegalArgumentException e) {
            log.error("Base64 decoding failed for public key: " + e.getMessage());
            throw e;
        } catch (Exception e) {
            log.error("Public key spec error: " + e.getMessage());
            throw e;
        }

    }

    public PrivateKey loadPrivateKey(String privateKey) throws Exception {
        try {
            KeyFactory kf = KeyFactory.getInstance("RSA");
            byte[] decodedPrivateKey = Base64.getDecoder().decode(privateKey);

            String rsaKey = new String(decodedPrivateKey, StandardCharsets.UTF_8);

            rsaKey = rsaKey.replace(PKCS_8_PEM_HEADER, "")
                    .replace(PKCS_8_PEM_FOOTER, "")
                    .replace("\n", "")
                    .replace("\r", "");
            byte[] keyContentAsBytes = Base64.getDecoder().decode(rsaKey);

            PKCS8EncodedKeySpec specPrivateKey = new PKCS8EncodedKeySpec(keyContentAsBytes);
            return kf.generatePrivate(specPrivateKey);
        } catch (IllegalArgumentException | InvalidKeySpecException | NoSuchAlgorithmException e) {
            log.error("Base64 decoding failed for private key: " + e.getMessage());
            throw e;
        } catch (RuntimeException e) {
            log.error("Private key spec error: " + e.getMessage());
            throw e;
        }
    }

    public SecurityRCAConfig(VaultTemplate vaultTemplate) {
        this.vaultTemplate = vaultTemplate;
    }

}
