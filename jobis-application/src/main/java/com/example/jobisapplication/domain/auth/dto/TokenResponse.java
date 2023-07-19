package com.example.jobisapplication.domain.auth.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class TokenResponse {
    private final String accessToken;
    private final LocalDateTime accessExpiresAt;
    private final String refreshToken;
    private final LocalDateTime refreshExpiresAt;
}
