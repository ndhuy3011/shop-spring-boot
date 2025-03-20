package com.ndhuy.auth.authentication.application.service.impl;

import org.springframework.stereotype.Service;

import com.ndhuy.auth.authentication.application.dto.AddSessionAuthUsernameIn;
import com.ndhuy.auth.authentication.application.dto.AddSessionAuthUsernameOut;
import com.ndhuy.auth.authentication.application.service.AddSessionUsernameAuth;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class AddSessionUsernameAuthImpl  implements AddSessionUsernameAuth{

    
    /** 
     * @param cplIn
     * @return AddSessionAuthUsernameOut
     */
    @Override
    public AddSessionAuthUsernameOut doMain(AddSessionAuthUsernameIn cplIn) {
      
        throw new UnsupportedOperationException("Unimplemented method 'doMain'");
    }
    
}   
