package team.retum.jobis.domain.company.presentation.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import team.retum.jobis.domain.company.dto.request.TeacherRegisterCompanyRequest;

@Getter
@NoArgsConstructor
public class TeacherRegisterCompanyWebRequest {
    @NotBlank
    @Size(max = 50)
    private String name;
    @NotBlank
    @NotNull
    @Size(min = 10, max = 10)
    private String businessNumber;
    String companyProfileUrl;

    public TeacherRegisterCompanyRequest toDomainRequest() {
        return TeacherRegisterCompanyRequest.builder()
                .name(this.name)
                .businessNumber(this.businessNumber)
                .companyProfileUrl(this.companyProfileUrl)
                .build();
    }
}
