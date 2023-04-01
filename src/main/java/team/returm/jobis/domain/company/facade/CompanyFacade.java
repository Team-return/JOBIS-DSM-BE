package team.returm.jobis.domain.company.facade;

import team.returm.jobis.domain.company.domain.repository.CompanyJpaRepository;
import team.returm.jobis.domain.company.exception.CompanyNotFoundException;
import team.returm.jobis.infrastructure.feignClients.BizNoFeignClient;
import team.returm.jobis.infrastructure.feignClients.FeignProperty;
import team.returm.jobis.infrastructure.feignClients.dto.Items;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class CompanyFacade {

    private final BizNoFeignClient bizNoFeignClient;
    private final FeignProperty feignProperty;
    private final CompanyJpaRepository companyJpaRepository;

    public String getCompanyName(String businessNumber) {
        Items items = getApi(businessNumber);
        return items.getCompany();
    }

    public boolean checkCompany(String businessNumber) {
        Items items = getApi(businessNumber);
        return items.getBno().replace("-", "").equals(businessNumber);
    }

    private Items getApi(String businessNumber) {
        try {
            return bizNoFeignClient.getApi(feignProperty.getAccessKey(),
                    1, "N", businessNumber, "json").getItems().get(0);
        } catch (Exception e) {
            throw CompanyNotFoundException.EXCEPTION;
        }
    }
}
