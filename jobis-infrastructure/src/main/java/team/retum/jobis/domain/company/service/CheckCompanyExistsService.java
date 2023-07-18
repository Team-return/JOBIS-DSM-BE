package team.retum.jobis.domain.company.service;

import lombok.RequiredArgsConstructor;
import team.retum.jobis.domain.company.persistence.repository.CompanyRepository;
import team.retum.jobis.domain.company.presentation.dto.response.CheckCompanyExistsResponse;
import team.retum.jobis.global.annotation.ReadOnlyService;
import team.retum.jobis.thirdparty.api.FeignUtil;

@RequiredArgsConstructor
@ReadOnlyService
public class CheckCompanyExistsService {

    private final FeignUtil feignUtil;
    private final CompanyRepository companyRepository;

    public CheckCompanyExistsResponse execute(String businessNumber) {
        String companyName = feignUtil.getCompanyName(businessNumber);
        boolean exists = companyRepository.existsCompanyByBizNo(businessNumber);

        return CheckCompanyExistsResponse.builder()
                .companyName(companyName)
                .exists(exists)
                .build();
    }
}
