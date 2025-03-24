package com.ndhuy.auth.authentication.application.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class AddSessionJwtIn {
    @NotEmpty
    private String jwtSession;
    private String jwtRefresh;
    private String issueAt;
    private String expiresAt;
}
