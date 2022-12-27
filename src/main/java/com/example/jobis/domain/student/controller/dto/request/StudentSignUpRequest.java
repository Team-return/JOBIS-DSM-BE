package com.example.jobis.domain.student.controller.dto.request;

import com.example.jobis.domain.student.domain.types.Gender;
import com.example.jobis.domain.student.domain.types.Grade;
import com.example.jobis.global.util.RegexProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@NoArgsConstructor
public class StudentSignUpRequest {

    @Pattern(regexp = RegexProperty.EMAIL, message = "이메일은 @dsm.hs.kr을 포함해야함")
    private String accountId;

    @Pattern(regexp = RegexProperty.PASSWORD, message = "비밀번호는 '숫자', '문자', '특수문자' 무조건 1개 이상, 비밀번호 '최소 8자에서 최대 16자'까지 허용")
    private String password;
    @NotBlank
    private Grade grade;
    @NotBlank
    private String name;
    @NotBlank
    private Gender gender;
}
