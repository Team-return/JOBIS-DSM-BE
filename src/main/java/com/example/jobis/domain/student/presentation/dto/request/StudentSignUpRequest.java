package com.example.jobis.domain.student.presentation.dto.request;

import com.example.jobis.domain.student.domain.types.Gender;
import com.example.jobis.global.util.RegexProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@NoArgsConstructor
public class StudentSignUpRequest {

    @Pattern(regexp = RegexProperty.EMAIL)
    private String email;

    @NotBlank
    @Size(min = 10, max = 12)
    private String phoneNumber;

    @Pattern(regexp = RegexProperty.PASSWORD)
    private String password;

    @NotNull
    private Integer grade;

    @NotBlank
    private String name;

    @NotNull
    private Integer classRoom;

    @NotNull
    private Integer number;

    @NotBlank
    private Gender gender;
}
