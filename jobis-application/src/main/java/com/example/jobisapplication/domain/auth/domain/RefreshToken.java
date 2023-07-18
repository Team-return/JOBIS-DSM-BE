package com.example.jobisapplication.domain.auth.domain;

import com.example.jobisapplication.common.annotation.Aggregate;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@Aggregate
public class RefreshToken {

    private final Long id;

    private final String token;

    private final Authority authority;

    private final Long ttl;

}
