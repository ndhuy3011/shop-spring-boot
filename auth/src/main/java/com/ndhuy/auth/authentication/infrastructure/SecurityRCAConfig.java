package com.ndhuy.auth.authentication.infrastructure;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.util.Objects;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.vault.core.VaultTemplate;
import org.springframework.vault.support.VaultResponse;

import com.ndhuy.auth.authentication.application.utils.DecryptionUtils;
import com.ndhuy.auth.authentication.application.utils.EncryptionUtils;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
public class SecurityRCAConfig {

    private final VaultTemplate vaultTemplate;

    private RSAPublicKey publicKeyRca;
    private RSAPrivateKey privateKeyRca;

    public SecurityRCAConfig(VaultTemplate vaultTemplate) {
        this.vaultTemplate = vaultTemplate;
    }

    @Bean
    RSAPublicKey publicKeyRca() {
        return this.publicKeyRca;
    }

    @Bean
    RSAPrivateKey privateKey() {
        return this.privateKeyRca;
    }

    @Bean
    JWKSource<SecurityContext> jwkSource() {
        RSAKey rsaKey = new com.nimbusds.jose.jwk.RSAKey.Builder(this.publicKeyRca)
                .privateKey(this.privateKeyRca)
                .build();
        JWKSet jwkSet = new JWKSet(rsaKey);
        return (jwkSelector, securityContext) -> jwkSelector.select(jwkSet);
    }

    @Bean
    JwtDecoder jwtDecoder() {
        return NimbusJwtDecoder.withPublicKey(this.publicKeyRca).build();
    }

    @Bean
    JwtEncoder jwtEncoder() {
        RSAKey rsaKey = new com.nimbusds.jose.jwk.RSAKey.Builder(this.publicKeyRca)
                .privateKey(this.privateKeyRca)
                .build();
        return new NimbusJwtEncoder(new ImmutableJWKSet<>(new JWKSet(rsaKey)));
    }

    @PostConstruct
    public void init() throws InvalidKeySpecException, NoSuchAlgorithmException, UnsupportedEncodingException,
            NoSuchProviderException {
        log.info("Loading Vault Key");

        VaultResponse response = vaultTemplate.read("authentication-client/authentication");
        Objects.requireNonNull(response, "Vault Null");
        var data = response.getData();
        Objects.requireNonNull(data, "Data Vault NULL");
        log.info("Loading Private key");
        // Load private key
        String privateKeyBase64 = data.get("private_key").toString();
        PrivateKey privateKey = EncryptionUtils.loadKeyWithBase64(privateKeyBase64);
        this.privateKeyRca = (RSAPrivateKey) privateKey;
        PublicKey publicKey = DecryptionUtils.loadPublicKeyWithPrivaKey(privateKey);
        this.publicKeyRca = (RSAPublicKey) publicKey;

    }

}
