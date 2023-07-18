package com.example.jobisapplication.domain.auth.dto;

import lombok.Getter;

@Getter
public class VerifyAuthCodeRequest {

    private String email;

    private String authCode;
}
