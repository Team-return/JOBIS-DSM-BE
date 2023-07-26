package com.example.jobisapplication.domain.student.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UpdatePasswordRequest {

    private String currentPassword;

    private String newPassword;
}
