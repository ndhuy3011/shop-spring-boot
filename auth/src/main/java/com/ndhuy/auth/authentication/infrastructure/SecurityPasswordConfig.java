package com.ndhuy.auth.authentication.infrastructure;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder.BCryptVersion;
import org.springframework.security.crypto.password.PasswordEncoder;

import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
public class SecurityPasswordConfig {

    
    /** 
     * @return PasswordEncoder
     */
    @Bean
    PasswordEncoder passwordEncoder() {
        log.info("Loading Password EnCoder");
        return new BCryptPasswordEncoder(BCryptVersion.$2Y);
    }

}
