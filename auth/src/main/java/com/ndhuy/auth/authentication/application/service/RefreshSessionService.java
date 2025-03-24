package com.ndhuy.auth.authentication.application.service;

import com.ndhuy.auth.authentication.application.dto.RefrestSessionDto.RefrestSessionOut;

public interface RefreshSessionService {
    RefrestSessionOut doMain(String jwt);
    
} 