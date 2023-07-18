package team.retum.jobis.domain.company.service;

import lombok.RequiredArgsConstructor;
import team.retum.jobis.domain.company.persistence.repository.CompanyRepository;
import com.example.jobisapplication.domain.company.dto.response.CheckCompanyExistsResponse;
import com.example.jobisapplication.common.annotation.ReadOnlyService;
import team.retum.jobis.thirdparty.api.FeignClientAdapter;

@RequiredArgsConstructor
@ReadOnlyService
public class CheckCompanyExistsService {

    private final FeignClientAdapter feignClientAdapter;
    private final CompanyRepository companyRepository;

    public CheckCompanyExistsResponse execute(String businessNumber) {
        String companyName = feignClientAdapter.getCompanyName(businessNumber);
        boolean exists = companyRepository.existsCompanyByBizNo(businessNumber);

        return CheckCompanyExistsResponse.builder()
                .companyName(companyName)
                .exists(exists)
                .build();
    }
}
