package com.example.jobisapplication.domain.acceptance.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class TokenResponse {
    private final String accessToken;
    private final LocalDateTime accessExpiresAt;
    private final String refreshToken;
    private final LocalDateTime refreshExpiresAt;
}
