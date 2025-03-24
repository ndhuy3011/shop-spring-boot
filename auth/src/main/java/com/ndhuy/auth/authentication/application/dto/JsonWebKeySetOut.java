package com.ndhuy.auth.authentication.application.dto;

import java.util.List;

import com.ndhuy.auth.authentication.domain.model.JsonWebKey;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class JsonWebKeySetOut {
    private List<JsonWebKey> keys;
}
