package com.example.jobis.domain.company.controller.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

@Getter
@NoArgsConstructor
public class RegisterCompanyRequest {

    @NotBlank(message = "사업자번호는 널 또는 공백을 포함할 수 없습니다.")
    @Size(min = 10, max = 10, message = "사업자 번호는 10글자여야 합니다.")
    private String businessNumber;

    @NotBlank(message = "비밀번호는 Null, 공백, 띄어쓰기를 허용하지 않습니다.")
    @Pattern(regexp = "(?=.*[a-z])(?=.*[0-9])(?=.*[!#$%&'()*+,./:;<=>?@＼^_`{|}~])[a-zA-Z0-9!#$%&'()*+,./:;" + "<=>?@＼^_`{|}~]{8,30}$", message = "비밀번호는 소문자, 숫자, 특수문자가 포함되어야 합니다.")
    private String password;

    @NotBlank(message = "회사소개는 널 또는 공백을 포함할 수 없습니다.")
    @Size(min = 1, max = 4000, message = "회사소개는 1자에서 4000자 사이여야합니다.")
    private String companyIntroduce;

    @NotBlank(message = "우편번호1은 널 또는 공백을 포함할 수 없습니다.")
    @Size(min = 5, max = 5, message = "우편번호1은 5자여야 합니다.")
    private String zipCode1;

    @NotBlank(message = "주소1는 널 또는 공백을 포함할 수 없습니다.")
    @Size(min = 1, max = 100, message = "주소1은 1자에서 100자 사이여야합니다.")
    private String address1;

    @Size(max = 5, message = "우편번호2는 5자여야 합니다.")
    private String zipCode2;

    @Size(max = 100, message = "주소2는 1자에서 100자 사이여야합니다.")
    private String address2;

    @NotBlank(message = "담당자1은 널 또는 공백을 포함할 수 없습니다.")
    @Size(min = 1, max = 40, message = "담당자1은 1자에서 40자여야 합니다.")
    private String manager1;

    @NotBlank(message = "담당자1 전화번호는 널 또는 공백을 포함할 수 없습니다.")
    @Size(min = 10, max = 11, message = "담당자1 전화번호는 10자에서 11자여야 합니다.")
    private String phoneNumber1;

    @Size(max = 40, message = "담당자2은 1자에서 40자여야 합니다.")
    private String manager2;

    @Size(max = 11, message = "담당자2 전화번호는 10자에서 11자여야 합니다.")
    private String phoneNumber2;

    @Size(max = 11, message = "팩스번호는 10자에서 11자여야 합니다.")
    private String fax;

    @Email
    @Size(min = 1, max = 60, message = "이메일은 1자에서 60자여야 합니다.")
    private String email;

    @NotBlank(message = "대표자는 널 또는 공백을 포함할 수 없습니다.")
    @Size(min = 1, max = 40, message = "대표자는 1자에서 40자여야 합니다.")
    private String representativeName;

    @NotBlank(message = "대표자는 널 또는 공백을 포함할 수 없습니다.")
    @Size(min = 8, max = 8, message = "대표자는 8자여야 합니다.")
    private String foundedAt;

    @NotNull(message = "근로자수는 널을 포함할 수 없습니다.")
    private Long workerNumber;

    @NotNull(message = "매출액은 널을 포함할 수 없습니다.")
    private Long take;

    @NotBlank(message = "회사 프로필 사진은 널 공백 포함 불가입니다.")
    private String companyProfileUrl;

}
