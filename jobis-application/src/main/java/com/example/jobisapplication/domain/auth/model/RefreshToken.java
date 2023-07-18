package com.example.jobisapplication.domain.auth.model;

import com.example.jobisapplication.common.annotation.Aggregate;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder(toBuilder = true)
@Aggregate
public class RefreshToken {

    private final Long id;

    private final String token;

    private final Authority authority;

    private final Long ttl;

    public RefreshToken update(String token, Long ttl) {
        return this.toBuilder()
                .token(token)
                .ttl(ttl)
                .build();
    }
}
