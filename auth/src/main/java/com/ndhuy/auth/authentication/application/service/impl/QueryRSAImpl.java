package com.ndhuy.auth.authentication.application.service.impl;

import java.security.interfaces.RSAPublicKey;
import java.util.Base64;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.ndhuy.auth.authentication.domain.model.JsonWebKey;
import com.ndhuy.auth.authentication.application.dto.JsonWebKeyDto.JsonWebKeySetOut;
import com.ndhuy.auth.authentication.application.service.QueryRSASerivce;

import jakarta.annotation.Resource;

@Service
@Scope("singleton")
public class QueryRSAImpl implements QueryRSASerivce, InitializingBean {

    @Resource
    RSAPublicKey rsaPublicKey;

    private static final String DEFAULT_SIGNATURE_ALGORITHM = "RS256"; // Hoặc RS384, RS512 tùy cấu hình
    private JsonWebKeySetOut jwksOut; // Store the result

    /**
     * Returns the pre-computed JWKS response. This method is optimized for multiple
     * calls.
     *
     * @return The JsonWebKeySetOut containing the JWKS information.
     */
    @Override
    public JsonWebKeySetOut jwks() {
        return this.jwksOut; // Return the pre-calculated value.

    }

    /**
     * Called after all necessary dependencies are injected by the container.
     * This method performs initialization tasks.
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        generateKeys();

    }

    private void generateKeys() {
        // Pre-calculate the JWKS output. This is done only once during the bean's
        // initialization.
        final String kid = UUID.randomUUID().toString();
        final JsonWebKey key = new JsonWebKey(
                "RSA",
                kid,
                "sig",
                Base64.getUrlEncoder().withoutPadding().encodeToString(rsaPublicKey.getModulus().toByteArray()),
                Base64.getUrlEncoder().withoutPadding().encodeToString(rsaPublicKey.getPublicExponent().toByteArray()),
                DEFAULT_SIGNATURE_ALGORITHM);
        this.jwksOut = new JsonWebKeySetOut(List.of(key));
    }
}
