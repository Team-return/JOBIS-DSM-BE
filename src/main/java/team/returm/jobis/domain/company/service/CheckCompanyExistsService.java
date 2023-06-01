package team.returm.jobis.domain.company.service;

import lombok.RequiredArgsConstructor;
import team.returm.jobis.domain.company.domain.repository.CompanyRepository;
import team.returm.jobis.domain.company.presentation.dto.response.CheckCompanyExistsResponse;
import team.returm.jobis.global.annotation.ReadOnlyService;
import team.returm.jobis.infrastructure.feignClients.FeignUtil;

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
