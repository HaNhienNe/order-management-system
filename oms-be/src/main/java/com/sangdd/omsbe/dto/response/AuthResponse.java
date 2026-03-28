package com.sangdd.omsbe.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Builder
public class AuthResponse {
    private String accessToken;
    public AuthResponse(String token) { this.accessToken = token; }
    // Getter
}