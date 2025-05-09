package team.retum.jobis.domain.company.presentation.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import team.retum.jobis.domain.company.dto.request.UpdateCompanyDetailsRequest;
import team.retum.jobis.global.util.RegexProperty;

import java.util.List;

@Getter
@NoArgsConstructor
public class UpdateCompanyDetailsWebRequest {

    @Size(max = 1000)
    private String companyIntroduce;

    @NotBlank
    @Size(min = 5, max = 5)
    private String mainZipCode;

    @NotBlank
    @Size(max = 50)
    private String mainAddress;

    @NotBlank
    @Size(max = 50)
    private String mainAddressDetail;

    @NotBlank
    @Size(max = 10)
    private String managerName;

    @NotBlank
    @Size(min = 9, max = 12)
    private String managerPhoneNo;

    @Email
    @Size(max = 30)
    @Pattern(regexp = RegexProperty.EMAIL)
    private String email;

    @NotNull
    private Integer workerNumber;

    @NotNull
    private Double take;

    @NotBlank
    private String companyProfileUrl;

    @NotBlank
    private String serviceName;

    @Size(min = 9, max = 12)
    private String representativePhoneNo;

    private List<@NotNull String> attachmentUrls;

    private String bizRegistrationUrl;

    @NotNull
    private Boolean headquarter;


    public UpdateCompanyDetailsRequest toDomainRequest() {
        return UpdateCompanyDetailsRequest.builder()
            .companyIntroduce(this.companyIntroduce)
            .mainZipCode(this.mainZipCode)
            .mainAddress(this.mainAddress)
            .mainAddressDetail(this.mainAddressDetail)
            .managerName(this.managerName)
            .managerPhoneNo(this.managerPhoneNo)
            .email(this.email)
            .workerNumber(this.workerNumber)
            .take(this.take)
            .companyProfileUrl(this.companyProfileUrl)
            .serviceName(this.serviceName)
            .representativePhoneNo(this.representativePhoneNo)
            .attachmentUrls(this.attachmentUrls)
            .bizRegistrationUrl(this.bizRegistrationUrl)
            .headquarter(this.headquarter)
            .build();
    }
}
