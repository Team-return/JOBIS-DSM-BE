package team.retum.jobis.domain.company.usecase;

import team.retum.jobis.common.annotation.ReadOnlyUseCase;
import team.retum.jobis.domain.company.dto.response.TeacherQueryEmployCompaniesResponse;
import team.retum.jobis.domain.company.model.CompanyType;
import team.retum.jobis.domain.company.spi.QueryCompanyPort;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@ReadOnlyUseCase
public class TeacherQueryEmployCompaniesUseCase {

    private final QueryCompanyPort queryCompanyPort;

    public TeacherQueryEmployCompaniesResponse execute(String companyName, CompanyType type, Integer year) {
        return new TeacherQueryEmployCompaniesResponse(
                queryCompanyPort.queryEmployCompanies(companyName, type, year)
        );
    }
}
