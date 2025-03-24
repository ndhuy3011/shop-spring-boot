package com.ndhuy.auth.authentication.application.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ndhuy.auth.authentication.application.dto.JsonWebKeySetOut;
import com.ndhuy.auth.authentication.application.service.QueryRSASerivce;

import jakarta.annotation.Resource;

@RestController
@RequestMapping("api/public/rsa")
public class RSARestPublic {
    @Resource
    QueryRSASerivce queryRsa;

    @GetMapping("/.well-known/jwks.json")
    public ResponseEntity<JsonWebKeySetOut> getJsonWebKeySet() {
        return ResponseEntity.ok().body(queryRsa.jwks());
    }

}
