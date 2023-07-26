package com.example.jobisapplication.domain.student.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UpdateForgottenPasswordRequest {

    private String email;

    private String password;
}
