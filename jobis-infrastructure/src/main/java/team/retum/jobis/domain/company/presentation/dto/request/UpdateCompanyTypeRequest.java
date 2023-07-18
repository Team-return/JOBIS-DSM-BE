package team.retum.jobis.domain.company.presentation.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import team.retum.jobis.domain.company.persistence.enums.CompanyType;

import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@NoArgsConstructor
public class UpdateCompanyTypeRequest {

    @NotNull
    private List<Long> companyIds;

    @NotNull
    private CompanyType companyType;
}
