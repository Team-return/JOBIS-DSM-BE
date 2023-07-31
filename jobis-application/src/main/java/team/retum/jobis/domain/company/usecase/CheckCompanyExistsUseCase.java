package team.retum.jobis.domain.company.usecase;

import team.retum.jobis.common.annotation.ReadOnlyUseCase;
import team.retum.jobis.common.spi.FeignClientPort;
import team.retum.jobis.domain.company.dto.response.CheckCompanyExistsResponse;
import team.retum.jobis.domain.company.spi.QueryCompanyPort;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@ReadOnlyUseCase
public class CheckCompanyExistsUseCase {

    private final FeignClientPort feignClientPort;
    private final QueryCompanyPort queryCompanyPort;

    public CheckCompanyExistsResponse execute(String businessNumber) {
        String companyName = feignClientPort.getCompanyNameByBizNo(businessNumber);
        boolean exists = queryCompanyPort.existsCompanyByBizNo(businessNumber);

        return CheckCompanyExistsResponse.builder()
                .companyName(companyName)
                .exists(exists)
                .build();
    }
}
