package com.example.jobis.domain.student.controller.dto.request;

import com.example.jobis.domain.student.domain.types.Gender;
import com.example.jobis.domain.student.domain.types.Grade;
import com.example.jobis.global.util.RegexProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@NoArgsConstructor
public class StudentSignUpRequest {

    @Pattern(regexp = RegexProperty.EMAIL, message = "email은 @dsm.hs.kr을 포함해야함")
    private String email;

    @NotBlank(message = "phone_number은 null 또는 공백을 포함할 수 없습니다.")
    @Size(min = 10, max = 12, message = "phone_number은 10자에서 11자여야 합니다.")
    private String phoneNumber;

    @Pattern(regexp = RegexProperty.PASSWORD, message = "비밀번호는 '숫자', '문자', '특수문자' 무조건 1개 이상, 비밀번호 '최소 8자에서 최대 16자'까지 허용")
    private String password;
    @NotBlank(message = "grade는 null 또는 공백을 포함할 수 없습니다.")
    private Grade grade;
    @NotBlank(message = "name은 null 또는 공백을 포함 할 수 없습니다.")
    private String name;
    @NotBlank(message = "gender는 null 또는 공백을 포함할 수 없습니다.")
    private Gender gender;
}
