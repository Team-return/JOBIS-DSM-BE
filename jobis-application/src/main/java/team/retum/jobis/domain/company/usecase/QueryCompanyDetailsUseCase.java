package team.retum.jobis.domain.company.usecase;

import lombok.RequiredArgsConstructor;
import team.retum.jobis.common.annotation.ReadOnlyUseCase;
import team.retum.jobis.domain.company.dto.response.QueryCompanyDetailsResponse;
import team.retum.jobis.domain.company.exception.CompanyNotFoundException;
import team.retum.jobis.domain.company.spi.QueryCompanyPort;
import team.retum.jobis.domain.company.spi.vo.CompanyDetailsVO;

@RequiredArgsConstructor
@ReadOnlyUseCase
public class QueryCompanyDetailsUseCase {

    private final QueryCompanyPort queryCompanyPort;

    public QueryCompanyDetailsResponse execute(Long companyId) {
        CompanyDetailsVO vo = queryCompanyPort.queryCompanyDetails(companyId)
                .orElseThrow(() -> CompanyNotFoundException.EXCEPTION);

        return QueryCompanyDetailsResponse.from(vo);
    }
}
