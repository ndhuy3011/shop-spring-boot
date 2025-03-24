package com.ndhuy.auth.authentication.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class JsonWebKey {
    private String kty;
    private String kid;
    private String use;
    private String n;
    private String e;
    private String alg;
}
