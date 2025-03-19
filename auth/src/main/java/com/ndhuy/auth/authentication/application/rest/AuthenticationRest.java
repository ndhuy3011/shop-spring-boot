package com.ndhuy.auth.authentication.application.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ndhuy.auth.authentication.application.dto.PermissionIn;
import com.ndhuy.auth.authentication.application.dto.PermissionOut;
import com.ndhuy.auth.authentication.application.service.PermissionService;

import jakarta.annotation.Resource;

@RestController
@RequestMapping("/auth")
public class AuthenticationRest {
    @Resource
    PermissionService permissionService;

    @PostMapping("/verify-user")
    public ResponseEntity<PermissionOut> postVerifyUser(final @RequestBody PermissionIn permissionIn) {
        return ResponseEntity.ok().body(permissionService.doMain(permissionIn));
    }

}
