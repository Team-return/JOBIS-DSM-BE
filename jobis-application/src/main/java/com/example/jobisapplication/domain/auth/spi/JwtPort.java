package com.example.jobisapplication.domain.auth.spi;

import com.example.jobisapplication.domain.auth.dto.TokenResponse;
import com.example.jobisapplication.domain.auth.model.Authority;

import java.time.LocalDateTime;

public interface JwtPort {

    Long getRefreshExp();

    TokenResponse generateTokens(Long userId, Authority authority);
}
