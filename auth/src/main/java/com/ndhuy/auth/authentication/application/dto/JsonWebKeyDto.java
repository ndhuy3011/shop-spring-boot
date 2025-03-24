package com.ndhuy.auth.authentication.application.dto;

import java.util.List;

import com.ndhuy.auth.authentication.domain.model.JsonWebKey;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class JsonWebKeyDto {
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class JsonWebKeySetOut {
        private List<JsonWebKey> keys;
    }

}
