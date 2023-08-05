package team.retum.jobis.domain.company.usecase;

import lombok.RequiredArgsConstructor;
import team.retum.jobis.common.annotation.ReadOnlyUseCase;
import team.retum.jobis.domain.company.dto.CompanyFilter;
import team.retum.jobis.domain.company.dto.response.StudentQueryCompaniesResponse;
import team.retum.jobis.domain.company.spi.QueryCompanyPort;

@RequiredArgsConstructor
@ReadOnlyUseCase
public class StudentQueryCompaniesUseCase {

    private final QueryCompanyPort queryCompanyPort;

    public StudentQueryCompaniesResponse execute(Long page, String name) {
        CompanyFilter filter = CompanyFilter.builder()
                .name(name)
                .page(page)
                .build();

        return new StudentQueryCompaniesResponse(
                queryCompanyPort.queryCompanyVoList(filter)
        );
    }
}
