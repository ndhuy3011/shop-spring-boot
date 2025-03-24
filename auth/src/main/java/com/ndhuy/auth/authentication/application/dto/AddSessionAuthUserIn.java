package com.ndhuy.auth.authentication.application.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AddSessionAuthUserIn {

    @NotNull
    private String username;
    @NotNull
    private String jwtSession;
}
