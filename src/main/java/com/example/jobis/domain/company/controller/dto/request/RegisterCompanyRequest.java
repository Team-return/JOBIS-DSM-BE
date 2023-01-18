package com.example.jobis.domain.company.controller.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.*;
import java.time.LocalDate;

@Getter
@NoArgsConstructor
public class RegisterCompanyRequest {

    @NotBlank(message = "name은 null또는 공백을 허용하지 않습니다.")
    private String name;

    @NotBlank(message = "business_number는 널 또는 공백을 포함할 수 없습니다.")
    @Size(min = 10, max = 10, message = "business_number는 10글자여야 합니다.")
    private String businessNumber;

    @NotNull(message = "password는 null을 혀용하지 않습니다.")
    @Pattern(regexp = "^[0-9]{4}$", message = "password 4자리 숫자로 구성되어야 합니다.")
    private String password;

    @NotEmpty(message = "company_introduce는 null과 공백을 허용하지 않습니다.")
    @Size(max = 4000, message = "company_introduce는 4000자를 넘을 수 없습니다.")
    private String companyIntroduce;

    @NotBlank(message = "zip_code1은 null 또는 공백을 포함할 수 없습니다.")
    @Size(min = 5, max = 5, message = "zip_code1은 5자여야 합니다.")
    private String zipCode1;

    @NotBlank(message = "address1은 null 또는 공백을 포함할 수 없습니다.")
    @Size(max = 100, message = "address1은 100자를 넘을 수 없습니다.")
    private String address1;

    @Size(max = 5, message = "zip_code2는 5자여야 합니다.")
    private String zipCode2;

    @Size(max = 100, message = "address2는 100자를 넘을 수 없습니다.")
    private String address2;

    @NotBlank(message = "manager1은 null 또는 공백을 포함할 수 없습니다.")
    @Size(max = 10, message = "manager1은 10자를 넘을 수 없습니다.")
    private String manager1;

    @NotBlank(message = "phone_number1은 null 또는 공백을 포함할 수 없습니다.")
    @Size(min = 10, max = 12, message = "phone_number1은 10자에서 11자여야 합니다.")
    private String phoneNumber1;

    @Size(max = 10, message = "manager2는 10자를 넘을 수 없습니다.")
    private String manager2;

    @Size(max = 12, message = "phone_number2는 10자에서 12자여야 합니다.")
    private String phoneNumber2;

    @Size(min = 10, max = 12, message = "fax는 10자에서 12자여야 합니다.")
    private String fax;

    @Email
    @Size(min = 1, max = 20, message = "email은 1자에서 20자여야 합니다.")
    private String email;

    @NotBlank(message = "representative_name은 null 또는 공백을 포함할 수 없습니다.")
    @Size(max = 40, message = "representative_name은 40자를 넘을 수 없습니다.")
    private String representativeName;

    @NotNull(message = "founded_at은 null일 수 없습니다.")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate foundedAt;

    @NotNull(message = "worker_number는 null을 포함할 수 없습니다.")
    private int workerNumber;

    @NotNull(message = "take는 null을 포함할 수 없습니다.")
    private int take;

    @NotBlank(message = "company_profile_url은 null 또는 공백 포함 불가입니다.")
    private String companyProfileUrl;

}
