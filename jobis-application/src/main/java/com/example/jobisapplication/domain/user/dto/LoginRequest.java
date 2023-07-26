package com.example.jobisapplication.domain.user.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class LoginRequest {

    private String accountId;

    private String password;
}