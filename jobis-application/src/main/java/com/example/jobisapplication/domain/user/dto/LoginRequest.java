package com.example.jobisapplication.domain.user.dto;

import lombok.Getter;

@Getter
public class LoginRequest {

    private String accountId;

    private String password;
}