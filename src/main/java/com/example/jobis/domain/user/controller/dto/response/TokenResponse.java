package com.example.jobis.domain.user.controller.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class TokenResponse {

    private String accessToken;
    private String refreshToken;
    private LocalDateTime accessExpiredAt;
}
