package com.example.jobis.domain.user.presentation.dto.response;

import com.example.jobis.domain.user.domain.enums.Authority;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class UserAuthResponse {
    private final String accessToken;
    private final String refreshToken;
    private final LocalDateTime accessExpiresAt;
    private final Authority authority;
}
