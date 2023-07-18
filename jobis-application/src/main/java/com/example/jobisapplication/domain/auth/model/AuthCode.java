package com.example.jobisapplication.domain.auth.model;

import com.example.jobisapplication.common.annotation.Aggregate;
import com.example.jobisapplication.domain.auth.exception.BadAuthCodeException;
import com.example.jobisapplication.domain.auth.exception.UnverifiedEmailException;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder(toBuilder = true)
@Aggregate
public class AuthCode {

    private final String email;

    private final String code;

    private final boolean isVerified;

    private final Integer ttl;

    public AuthCode verify() {
        return this.toBuilder()
                .isVerified(true)
                .build();
    }

    public void checkIsVerified() {
        if (!this.isVerified) {
            throw UnverifiedEmailException.EXCEPTION;
        }
    }

    public void verifyCode(String code) {
        if (!this.code.equals(code)) {
            throw BadAuthCodeException.EXCEPTION;
        }
    }
}
