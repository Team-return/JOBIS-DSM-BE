package com.example.jobisapplication.domain.auth.spi;

import com.example.jobisapplication.domain.auth.model.RefreshToken;

public interface CommandRefreshTokenPort {

    void saveRefreshToken(RefreshToken refreshToken);

}
