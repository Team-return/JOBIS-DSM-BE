package com.example.jobisapplication.domain.student.dto;

import lombok.Getter;

@Getter
public class UpdateForgottenPasswordRequest {

    private String email;

    private String password;
}
