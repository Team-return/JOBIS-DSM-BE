package com.example.jobisapplication.domain.user.model;

import com.example.jobisapplication.common.annotation.Aggregate;
import com.example.jobisapplication.domain.auth.model.Authority;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@Aggregate
public class User {

    private final Long id;

    private final String accountId;

    private final String password;

    private final Authority authority;

}
