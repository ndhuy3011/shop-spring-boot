package com.ndhuy.auth.user.application.dto;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class GetInfoUserOut {
    UUID id;
    String username;
    String address;
    String fullName;
    String email;
}
