package com.example.jobisapplication.domain.auth.model;

import com.example.jobisapplication.common.annotation.Aggregate;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@Aggregate
public class AuthCode {

    private final String email;

    private final String code;

    private final boolean isVerified;

    private final Integer ttl;
}
