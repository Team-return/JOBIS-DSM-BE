package com.example.jobis.domain.user.controller.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@NoArgsConstructor
public class LoginRequest {

    @NotBlank(message = "사업자번호는 널 또는 공백을 포함할 수 없습니다.")
    @Size(min = 1, max = 20, message = "아이디는 1~20글자여야 합니다.")
    private String accountId;

    @NotBlank(message = "비밀번호는 Null, 공백, 띄어쓰기를 허용하지 않습니다.")
    @Pattern(regexp = "(?=.*[a-z])(?=.*[0-9])(?=.*[!#$%&'()*+,./:;<=>?@＼^_`{|}~])[a-zA-Z0-9!#$%&'()*+,./:;" + "<=>?@＼^_`{|}~]{8,30}$", message = "비밀번호는 소문자, 숫자, 특수문자가 포함되어야 합니다.")
    private String password;
}
