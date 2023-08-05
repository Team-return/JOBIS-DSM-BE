package team.retum.jobis.domain.company.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import team.retum.jobis.domain.company.model.CompanyType;

import java.util.List;

@Getter
@AllArgsConstructor
public class UpdateCompanyTypeRequest {

    private List<Long> companyIds;

    private CompanyType companyType;
}
