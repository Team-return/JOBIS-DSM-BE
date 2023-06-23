package team.retum.jobis.domain.company.presentation.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@NoArgsConstructor
public class UpdateCompanyDetailsRequest {

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
    @NotNull
    @Size(min = 1, max = 20, message = "email은 1자에서 20자여야 합니다.")
    private String email;

    @NotNull(message = "worker_number는 null을 포함할 수 없습니다.")
    private int workerNumber;

    @NotNull(message = "take는 null을 포함할 수 없습니다.")
    private double take;

    @NotBlank(message = "company_profile_url은 null 또는 공백 포함 불가입니다.")
    private String companyProfileUrl;

}
