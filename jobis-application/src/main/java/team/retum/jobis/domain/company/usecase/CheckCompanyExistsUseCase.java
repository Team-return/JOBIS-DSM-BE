package team.retum.jobis.domain.company.usecase;

import lombok.RequiredArgsConstructor;
import team.retum.jobis.common.annotation.ReadOnlyUseCase;
import team.retum.jobis.common.spi.FeignClientPort;
import team.retum.jobis.domain.company.dto.response.CheckCompanyExistsResponse;
import team.retum.jobis.domain.company.spi.QueryCompanyPort;

@RequiredArgsConstructor
@ReadOnlyUseCase
public class CheckCompanyExistsUseCase {

    private final FeignClientPort feignClientPort;
    private final QueryCompanyPort queryCompanyPort;

    public CheckCompanyExistsResponse execute(String businessNumber) {
        return queryCompanyPort.getCompanyByBusinessNumber(businessNumber)
            .map(c -> new CheckCompanyExistsResponse(c.getName(), true))
            .orElse(new CheckCompanyExistsResponse(feignClientPort.getCompanyNameByBizNo(businessNumber), false));
    }
}
