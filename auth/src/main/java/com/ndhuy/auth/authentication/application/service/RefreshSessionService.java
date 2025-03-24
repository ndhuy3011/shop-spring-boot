package com.ndhuy.auth.authentication.application.service;

import com.ndhuy.auth.authentication.application.dto.RefrestSessionOut;

public interface RefreshSessionService {
    RefrestSessionOut doMain(String jwt);
    
} 