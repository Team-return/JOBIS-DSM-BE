package team.retum.jobis.domain.company.presentation.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import team.retum.jobis.domain.company.dto.request.RegisterCompanyRequest;
import team.retum.jobis.global.util.RegexProperty;

import java.time.LocalDate;
import java.util.List;

@Getter
@NoArgsConstructor
public class RegisterCompanyWebRequest {

    @NotBlank
    @Size(max = 50)
    private String name;

    @NotBlank
    @Size(min = 10, max = 10)
    private String businessNumber;

    @NotEmpty
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
    private String managerPhoneNo;

    @Email
    @Size(max = 30)
    @Pattern(regexp = RegexProperty.EMAIL)
    private String email;

    @NotBlank
    @Size(max = 10)
    private String representativeName;

    @Size(min = 9, max = 12)
    private String representativePhoneNo;

    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate foundedAt;

    @NotNull
    @Max(32767)
    private Integer workerNumber;

    @NotNull
    private Double take;

    private String companyProfileUrl;

    private String bizRegistrationUrl;

    @NotNull
    private Long businessAreaCode;

    @NotBlank
    @Size(max = 80)
    private String serviceName;

    private List<String> attachmentUrls;

    @NotNull
    private Boolean headquarter;

    public RegisterCompanyRequest toDomainRequest() {
        return RegisterCompanyRequest.builder()
            .name(this.name)
            .businessNumber(this.businessNumber)
            .companyIntroduce(this.companyIntroduce)
            .mainZipCode(this.mainZipCode)
            .mainAddress(this.mainAddress)
            .mainAddressDetail(this.mainAddressDetail)
            .managerName(this.managerName)
            .managerPhoneNo(this.managerPhoneNo)
            .email(this.email)
            .representativeName(this.representativeName)
            .foundedAt(this.foundedAt)
            .workerNumber(this.workerNumber)
            .take(this.take)
            .companyProfileUrl(this.companyProfileUrl)
            .bizRegistrationUrl(this.bizRegistrationUrl)
            .businessAreaCode(this.businessAreaCode)
            .serviceName(this.serviceName)
            .attachmentUrls(this.attachmentUrls)
            .headquarter(this.headquarter)
            .build();
    }
}
