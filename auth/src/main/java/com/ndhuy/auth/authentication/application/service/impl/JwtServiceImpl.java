package com.ndhuy.auth.authentication.application.service.impl;

import java.time.Duration;
import java.time.Instant;
import java.util.Map;

import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import com.ndhuy.auth.authentication.application.dto.PermissionOut;
import com.ndhuy.auth.authentication.application.service.JwtService;
import com.ndhuy.auth.user.application.dto.GetInfoUserOut;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class JwtServiceImpl implements JwtService {

    @Resource
    JwtEncoder jwtEncoder;
    @Resource
    JwtDecoder jwtDecoder;

    
    /** 
     * @param cplIn
     * @return PermissionOut
     */
    @Override
    public PermissionOut generatorJWT(GetInfoUserOut cplIn) {
        log.info(log.getName() + " generator JWT ");
        var issueAt = Instant.now();
        var expiresAt = issueAt.plus(Duration.ofHours(1));
        var claim = JwtClaimsSet.builder()
                .id(cplIn.getUsername())
                .claim("uuid", cplIn.getId())
                .issuedAt(issueAt)
                .expiresAt(expiresAt)
                .build();
        var jwt = jwtEncoder.encode(JwtEncoderParameters.from(claim)).getTokenValue();
        return PermissionOut.builder()
                .jwt(jwt)
                .issueAt(issueAt.toString())
                .expiresAt(expiresAt.toString()).build();

    }

    @Override
    public Map<String, Object> parseJWT(String token) {
        return jwtDecoder.decode(token).getClaims();
    }

}
