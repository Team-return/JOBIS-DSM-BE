package com.example.jobisapplication.domain.acceptance.spi;

import com.example.jobisapplication.domain.acceptance.dto.TokenResponse;
import com.example.jobisapplication.domain.auth.model.Authority;

public interface JwtPort {

    TokenResponse generateTokens(Long userId, Authority authority);
}