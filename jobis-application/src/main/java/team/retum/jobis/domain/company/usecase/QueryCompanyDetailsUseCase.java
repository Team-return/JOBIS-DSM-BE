package team.retum.jobis.domain.company.usecase;

import lombok.RequiredArgsConstructor;
import team.retum.jobis.common.annotation.ReadOnlyUseCase;
import team.retum.jobis.common.spi.PublishEventPort;
import team.retum.jobis.common.spi.SecurityPort;
import team.retum.jobis.domain.company.dto.response.QueryCompanyDetailsResponse;
import team.retum.jobis.domain.company.event.RecentCompanyEvent;
import team.retum.jobis.domain.company.exception.CompanyNotFoundException;
import team.retum.jobis.domain.company.spi.QueryCompanyPort;
import team.retum.jobis.domain.company.spi.vo.CompanyDetailsVO;

@RequiredArgsConstructor
@ReadOnlyUseCase
public class QueryCompanyDetailsUseCase {

    private final QueryCompanyPort queryCompanyPort;
    private final SecurityPort securityPort;
    private final PublishEventPort eventPublisher;

    public QueryCompanyDetailsResponse execute(Long companyId) {
        CompanyDetailsVO vo = queryCompanyPort.getCompanyDetails(companyId)
            .orElseThrow(() -> CompanyNotFoundException.EXCEPTION);

        eventPublisher.publishEvent(new RecentCompanyEvent(securityPort.getCurrentUserId(), companyId));

        return QueryCompanyDetailsResponse.from(vo);
    }
}
