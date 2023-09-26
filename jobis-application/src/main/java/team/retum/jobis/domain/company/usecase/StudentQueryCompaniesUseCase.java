package team.retum.jobis.domain.company.usecase;

import lombok.RequiredArgsConstructor;
import team.retum.jobis.common.annotation.ReadOnlyUseCase;
import team.retum.jobis.common.dto.response.TotalPageCountResponse;
import team.retum.jobis.common.util.NumberUtil;
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
                .limit(12)
                .build();

        return new StudentQueryCompaniesResponse(
                queryCompanyPort.queryStudentCompanies(filter)
        );
    }

    public TotalPageCountResponse getCount(Long page, String name) {
        CompanyFilter filter = CompanyFilter.builder()
                .name(name)
                .page(page)
                .limit(12)
                .build();

        int totalPageCount = NumberUtil.getTotalPageCount(
                queryCompanyPort.getTotalCompanyCount(filter), filter.getLimit()
        );

        return new TotalPageCountResponse(totalPageCount);
    }
}
