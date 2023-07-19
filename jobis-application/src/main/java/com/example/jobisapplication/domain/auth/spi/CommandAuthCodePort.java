package com.example.jobisapplication.domain.auth.spi;

import com.example.jobisapplication.domain.auth.model.AuthCode;

public interface CommandAuthCodePort {

    void saveAuthCode(AuthCode authCode);
}
