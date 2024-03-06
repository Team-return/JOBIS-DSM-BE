package team.retum.jobis.domain.company.presentation.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
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

    @Size(min = 5, max = 5)
    private String subZipCode;

    @Size(max = 50)
    private String subAddress;

    @Size(max = 50)
    private String subAddressDetail;

    @NotBlank
    @Size(max = 10)
    private String managerName;

    @NotBlank
    @Size(min = 10, max = 12)
    private String managerPhoneNo;

    @Size(max = 10)
    private String subManagerName;

    @Size(max = 12)
    private String subManagerPhoneNo;

    @Size(min = 10, max = 12)
    private String fax;

    @Email
    @Size(max = 30)
    private String email;

    @NotBlank
    @Size(max = 10)
    private String representativeName;

    @NotBlank
    @Size(min = 10, max = 12)
    private String representativePhoneNo;

    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate foundedAt;

    @NotNull
    private int workerNumber;

    @NotNull
    private double take;

    private String companyProfileUrl;

    private String bizRegistrationUrl;

    @NotNull
    private Long businessAreaCode;

    @NotBlank
    @Size(max = 80)
    private String serviceName;

    private List<String> attachmentUrls;

    public RegisterCompanyRequest toDomainRequest() {
        return RegisterCompanyRequest.builder()
                .name(this.name)
                .businessNumber(this.businessNumber)
                .companyIntroduce(this.companyIntroduce)
                .mainZipCode(this.mainZipCode)
                .mainAddress(this.mainAddress)
                .mainAddressDetail(this.mainAddressDetail)
                .subZipCode(this.subZipCode)
                .subAddress(this.subAddress)
                .subAddressDetail(this.subAddressDetail)
                .managerName(this.managerName)
                .managerPhoneNo(this.managerPhoneNo)
                .subManagerName(this.subManagerName)
                .subManagerPhoneNo(this.subManagerPhoneNo)
                .fax(this.fax)
                .email(this.email)
                .representativeName(this.representativeName)
                .representativePhoneNo(this.representativePhoneNo)
                .foundedAt(this.foundedAt)
                .workerNumber(this.workerNumber)
                .take(this.take)
                .companyProfileUrl(this.companyProfileUrl)
                .bizRegistrationUrl(this.bizRegistrationUrl)
                .businessAreaCode(this.businessAreaCode)
                .serviceName(this.serviceName)
                .attachmentUrls(this.attachmentUrls)
                .build();
    }
}
