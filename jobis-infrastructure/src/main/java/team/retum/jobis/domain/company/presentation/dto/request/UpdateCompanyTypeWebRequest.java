package team.retum.jobis.domain.company.presentation.dto.request;

import team.retum.jobis.domain.company.dto.request.UpdateCompanyTypeRequest;
import lombok.Getter;
import lombok.NoArgsConstructor;
import team.retum.jobis.domain.company.model.CompanyType;

import javax.validation.constraints.NotNull;
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
