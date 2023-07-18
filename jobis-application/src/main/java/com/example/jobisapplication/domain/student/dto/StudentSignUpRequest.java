package com.example.jobisapplication.domain.student.dto;

import com.example.jobisapplication.domain.student.model.Gender;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class StudentSignUpRequest {

    private String email;

    private String password;

    private Integer grade;

    private String name;

    private Integer classRoom;

    private Integer number;

    private Gender gender;

    private String profileImageUrl;
}
