package com.ndhuy.auth.authentication.application.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ndhuy.auth.authentication.application.dto.PermissionDto.PermissionIn;
import com.ndhuy.auth.authentication.application.dto.PermissionDto.PermissionOut;
import com.ndhuy.auth.authentication.application.service.PermissionService;
import com.ndhuy.auth.user.application.dto.OpenAccountIn;
import com.ndhuy.auth.user.application.dto.OpenAccountOut;
import com.ndhuy.auth.user.application.service.OpenAccountService;

import jakarta.annotation.Resource;
import jakarta.validation.Valid;

@RestController
@RequestMapping("api/public/auth")
public class AuthenticationRestPublic {
    @Resource
    PermissionService permissionService;

    @Resource
    OpenAccountService openAccountService;

    /**
     * @param permissionIn
     * @return ResponseEntity<PermissionOut>
     */
    @PostMapping("/verify-user")
    public ResponseEntity<PermissionOut> postVerifyUser(final @Valid @RequestBody PermissionIn permissionIn) {
        return ResponseEntity.ok().body(permissionService.doMain(permissionIn));
    }

    /**
     * 
     * @param openAccountIn
     * @return ResponseEntity<OpenAccountOut>
     */
    @PostMapping("/create-user")
    public ResponseEntity<OpenAccountOut> postMethodName(final @Valid @RequestBody OpenAccountIn openAccountIn) {
        return ResponseEntity.status(HttpStatus.CREATED).body(openAccountService.doMain(openAccountIn));
    }

}
