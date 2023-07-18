package com.example.jobisapplication.domain.auth.spi;

import com.example.jobisapplication.domain.auth.dto.TokenResponse;
import com.example.jobisapplication.domain.auth.model.Authority;

public interface JwtPort {

    TokenResponse generateTokens(Long userId, Authority authority);
}
