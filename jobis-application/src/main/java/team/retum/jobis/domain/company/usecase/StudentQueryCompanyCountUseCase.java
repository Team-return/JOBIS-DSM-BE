package team.retum.jobis.domain.company.usecase;

import lombok.RequiredArgsConstructor;
import team.retum.jobis.common.annotation.ReadOnlyUseCase;
import team.retum.jobis.common.util.NumberUtil;
import team.retum.jobis.domain.company.dto.CompanyFilter;
import team.retum.jobis.domain.company.dto.response.StudentQueryCompanyCountResponse;
import team.retum.jobis.domain.company.spi.QueryCompanyPort;

@RequiredArgsConstructor
@ReadOnlyUseCase
public class StudentQueryCompanyCountUseCase {

    private final QueryCompanyPort queryCompanyPort;

    public StudentQueryCompanyCountResponse execute(Long page, String name) {
        CompanyFilter filter = CompanyFilter.builder()
                .name(name)
                .page(page)
                .limit(12)
                .build();

        int totalPageCount = NumberUtil.getTotalPageCount(
                queryCompanyPort.getTotalCompanyCount(filter), filter.getLimit()
        );

        return new StudentQueryCompanyCountResponse(totalPageCount);
    }
}
