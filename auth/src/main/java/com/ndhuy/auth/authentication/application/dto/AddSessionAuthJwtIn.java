package com.ndhuy.auth.authentication.application.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class AddSessionAuthJwtIn {
    @NotEmpty
    private String username;
    @NotEmpty
    private String jwtSession;
    private String issueAt;
    private String expiresAt;
}
