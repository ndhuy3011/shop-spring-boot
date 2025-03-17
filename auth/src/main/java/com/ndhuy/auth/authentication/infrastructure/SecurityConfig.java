package com.ndhuy.auth.authentication.infrastructure;

import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.Objects;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.vault.core.VaultTemplate;
import org.springframework.vault.support.VaultResponse;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;

@Configuration
@EnableWebSecurity
@Slf4j
public class SecurityConfig {

    private final VaultTemplate vaultTemplate;
    private RSAPublicKey publicKeyRca;

    public SecurityConfig(VaultTemplate vaultTemplate) {
        this.vaultTemplate = vaultTemplate; // VaultTemplate được inject
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        log.info("SecurityConfig");
        http.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().permitAll())
                .httpBasic(Customizer.withDefaults());

        return http.build();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @PostConstruct
    public void init() throws InvalidKeySpecException, NoSuchAlgorithmException {
        // Chạy sau khi VaultTemplate sẵn sàng
        VaultResponse response = vaultTemplate.read("secret/data/rca-public");
        Objects.requireNonNull(response, "Vault Null");

        var data = response.getData();

        Objects.requireNonNull(data, "Data Vault NULL");
        String publicKeyPem = data.get("public_key").toString();
        String pemContent = publicKeyPem.replace("-----BEGIN CERTIFICATE-----", "")
                .replace("-----END CERTIFICATE-----", "").replaceAll("\\s", "");
        byte[] decoded = Base64.getDecoder().decode(pemContent);
        X509EncodedKeySpec spec = new X509EncodedKeySpec(decoded);
        KeyFactory kf = KeyFactory.getInstance("RSA");
        this.publicKeyRca = (RSAPublicKey) kf.generatePublic(spec);
    }

    @Bean
    public RSAPublicKey publicKeyRca() {
        return this.publicKeyRca; // Trả về giá trị đã khởi tạo
    }

}
