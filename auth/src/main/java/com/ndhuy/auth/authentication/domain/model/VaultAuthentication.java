package com.ndhuy.auth.authentication.domain.model;

import org.springframework.context.annotation.Scope;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Scope("singleton")
public class VaultAuthentication {
    String privateKey;
    String publicKey;
}
