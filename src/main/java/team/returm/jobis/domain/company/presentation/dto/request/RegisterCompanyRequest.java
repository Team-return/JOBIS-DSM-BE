package team.returm.jobis.domain.company.presentation.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import team.returm.jobis.global.annotation.ValidListElements;
import team.returm.jobis.global.util.RegexProperty;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.List;

@Getter
@NoArgsConstructor
public class RegisterCompanyRequest {

    @NotBlank
    private String name;

    @NotBlank
    @Size(min = 10, max = 10)
    private String businessNumber;

    @NotNull
    @Pattern(regexp = RegexProperty.COMPANY_PASSWORD)
    private String password;

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

    @Size(max = 5)
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
    @Size(min = 1, max = 30)
    private String email;

    @NotBlank
    @Size(max = 40)
    private String representativeName;

    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate foundedAt;

    @NotNull
    private int workerNumber;

    @NotNull
    private double take;

    @NotBlank
    private String companyProfileUrl;

    @NotBlank
    private String bizRegistrationUrl;

    @NotNull
    private Long businessAreaCode;

    @NotBlank
    @Size(min = 1, max = 20)
    private String serviceName;

    @ValidListElements
    private List<String> attachmentUrls;
}
