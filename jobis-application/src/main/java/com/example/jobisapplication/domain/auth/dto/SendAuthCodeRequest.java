package com.example.jobisapplication.domain.auth.dto;

import lombok.Getter;

@Getter
public class SendAuthCodeRequest {

    private String email;

    private AuthCodeType authCodeType;
}
