package com.ndhuy.auth.authentication.application.service.proxy;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import com.ndhuy.auth.authentication.application.dto.AddSessionAuthJwtIn;
import com.ndhuy.auth.authentication.application.dto.AddSessionAuthJwtOut;
import com.ndhuy.auth.authentication.application.service.AddSessionJwtAuth;
import com.ndhuy.auth.authentication.application.service.impl.AddSessionAuthImpl;

import jakarta.annotation.Resource;
import jakarta.validation.Valid;

@Primary
@Component
public class AddSessionJwtAuthProxy implements AddSessionJwtAuth {

    @Resource
    AddSessionAuthImpl addSessionAuthImpl;

    
    /** 
     * @param cplin
     * @return AddSessionAuthJwtOut
     */
    @Override
    public AddSessionAuthJwtOut doMain(@Valid AddSessionAuthJwtIn cplin) {
        addSessionAuthImpl.checkMain(cplin);
        return addSessionAuthImpl.doMain(cplin);
    }

}
