package com.example.jobisapplication.domain.auth.spi;

import com.example.jobisapplication.domain.auth.model.AuthCode;

public interface QueryAuthCodePort {

    AuthCode queryAuthCodeByEmail(String email);

    boolean existsAuthCodeByEmailAndVerifiedIsTrue(String email);
}
