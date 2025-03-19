package com.ndhuy.auth.authentication.application.service.impl;

import java.time.Duration;
import java.time.Instant;
import java.util.Map;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import com.ndhuy.auth.authentication.application.dto.PermissionIn;
import com.ndhuy.auth.authentication.application.dto.PermissionOut;
import com.ndhuy.auth.authentication.application.service.JwtService;
import com.ndhuy.auth.authentication.application.service.PermissionService;
import com.ndhuy.auth.exception.domain.PasswordRuntimeException;
import com.ndhuy.auth.user.application.dto.GetInfoUserOut;
import com.ndhuy.auth.user.application.service.QueryUserService;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class PermissionServiceImpl implements PermissionService, JwtService {

    @Resource
    QueryUserService queryUserService;
    @Resource
    PasswordEncoder passwordEncoder;
    @Resource
    JwtEncoder jwtEncoder;
    @Resource
    JwtDecoder jwtDecoder;

    @Override
    public PermissionOut doMain(PermissionIn cplIn) {
        log.info(log.getName() + " Service Domain");

        var userDetails = queryUserService.loadUserByUsername(cplIn.getUsername());
        if (!passwordEncoder.matches(cplIn.getPassword(), userDetails.getPassword())) {
            throw new PasswordRuntimeException();
        }
        var user = queryUserService.getUser(cplIn.getUsername());
        return generatorJWT(user);
    }

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
