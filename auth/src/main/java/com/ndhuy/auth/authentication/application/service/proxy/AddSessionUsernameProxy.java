package com.ndhuy.auth.authentication.application.service.proxy;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import com.ndhuy.auth.authentication.application.dto.AddSessionAuthUsernameIn;
import com.ndhuy.auth.authentication.application.dto.AddSessionAuthUsernameOut;
import com.ndhuy.auth.authentication.application.service.AddSessionUsernameAuth;

@Primary
@Component
public class AddSessionUsernameProxy implements AddSessionUsernameAuth {

    @Override
    public AddSessionAuthUsernameOut doMain(AddSessionAuthUsernameIn cplIn) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'doMain'");
    }
    
}
