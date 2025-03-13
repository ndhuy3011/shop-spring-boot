package com.ndhuy.auth.authentication.application.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.vault.core.VaultTemplate;
import org.springframework.vault.support.VaultResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/auth")
public class AuthenticationRest {  
    @Autowired
    private VaultTemplate vaultTemplate;

    @GetMapping("path")
    public String getMethodName() {
        VaultResponse response = vaultTemplate.read("http://localhost:8200/v1/secret/data/authentication");
        return response.getData().get("data").toString();
    }
    
    
}
