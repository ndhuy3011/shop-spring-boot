package com.ndhuy.auth.authentication.application.service.impl;

import org.springframework.stereotype.Service;

import com.ndhuy.auth.authentication.application.service.IVaultService;

@Service
public class VaultService implements IVaultService{

    @Override
    public String getPublickey() {
        throw new UnsupportedOperationException("Unimplemented method 'getPublickey'");
    }

    @Override
    public String getPrivateKey() {
        throw new UnsupportedOperationException("Unimplemented method 'getPrivateKey'");
    }
    
}
