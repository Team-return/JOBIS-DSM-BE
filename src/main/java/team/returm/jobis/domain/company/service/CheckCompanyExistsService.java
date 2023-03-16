package team.returm.jobis.domain.company.service;

import team.returm.jobis.domain.company.domain.repository.CompanyRepository;
import team.returm.jobis.domain.company.presentation.dto.response.CheckCompanyExistsResponse;
import team.returm.jobis.domain.company.facade.CompanyFacade;
import team.returm.jobis.global.annotation.ReadOnlyService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@ReadOnlyService
public class CheckCompanyExistsService {

    private final CompanyFacade companyFacade;
    private final CompanyRepository companyRepository;

    public CheckCompanyExistsResponse execute(String businessNumber) {
        String companyName = companyFacade.getCompanyName(businessNumber);

        boolean exists = companyRepository.existsCompanyByBizNo(businessNumber);

        return CheckCompanyExistsResponse.builder()
                .companyName(companyName)
                .exists(exists)
                .build();
    }
}
