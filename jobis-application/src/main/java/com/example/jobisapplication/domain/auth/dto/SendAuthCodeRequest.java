package com.example.jobisapplication.domain.auth.dto;

import com.example.jobisapplication.domain.auth.model.AuthCodeType;
import lombok.Getter;

@Getter
public class SendAuthCodeRequest {

    private String email;

    private AuthCodeType authCodeType;
}
