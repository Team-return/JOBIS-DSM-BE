package com.example.jobis.domain.student.controller.dto.request;

import com.example.jobis.domain.student.domain.types.Gender;
import com.example.jobis.domain.student.domain.types.Grade;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class StudentSignUpRequest {

    private String accountId;
    private String password;
    private Grade grade;
    private String name;
    private Gender gender;
}
