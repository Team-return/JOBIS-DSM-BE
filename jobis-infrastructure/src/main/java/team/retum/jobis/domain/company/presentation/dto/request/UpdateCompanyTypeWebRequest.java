package team.retum.jobis.domain.company.presentation.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import team.retum.jobis.domain.company.dto.request.UpdateCompanyTypeRequest;
import team.retum.jobis.domain.company.model.CompanyType;

import jakarta.validation.constraints.NotNull;
import java.util.List;

@Getter
@NoArgsConstructor
public class UpdateCompanyTypeWebRequest {

    @NotNull
    private List<Long> companyIds;

    @NotNull
    private CompanyType companyType;

    public UpdateCompanyTypeRequest toDomainRequest() {
        return new UpdateCompanyTypeRequest(this.companyIds, this.companyType);
    }
}
