package com.example.jobisapplication.domain.student.dto;

import lombok.Getter;

@Getter
public class UpdatePasswordRequest {

    private String currentPassword;

    private String newPassword;
}
