package team.retum.jobis.domain.company.dto.request;

import team.retum.jobis.domain.company.model.CompanyType;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class UpdateCompanyTypeRequest {

    private List<Long> companyIds;

    private CompanyType companyType;
}
