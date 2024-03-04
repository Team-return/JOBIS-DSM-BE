package team.retum.jobis.domain.company.usecase;

import lombok.RequiredArgsConstructor;
import team.retum.jobis.common.annotation.ReadOnlyUseCase;
import team.retum.jobis.common.spi.SecurityPort;
import team.retum.jobis.domain.company.dto.response.CompanyMyPageResponse;
import team.retum.jobis.domain.company.model.Company;

@RequiredArgsConstructor
@ReadOnlyUseCase
public class CompanyMyPageUseCase {

    private final SecurityPort securityPort;

    public CompanyMyPageResponse execute() {
        Company company = securityPort.getCurrentCompany();

        return CompanyMyPageResponse.from(company);
    }
}
