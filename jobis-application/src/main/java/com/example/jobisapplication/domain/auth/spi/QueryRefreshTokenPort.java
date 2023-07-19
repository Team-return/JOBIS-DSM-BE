package com.example.jobisapplication.domain.auth.spi;

import com.example.jobisapplication.domain.auth.model.RefreshToken;

public interface QueryRefreshTokenPort {

    RefreshToken queryRefreshTokenByToken(String token);

}
