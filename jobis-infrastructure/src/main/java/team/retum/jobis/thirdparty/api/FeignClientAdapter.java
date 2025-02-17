package team.retum.jobis.thirdparty.api;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import team.retum.jobis.common.spi.FeignClientPort;
import team.retum.jobis.domain.company.exception.CompanyNotExistsException;
import team.retum.jobis.thirdparty.api.client.BizNoFeignClient;
import team.retum.jobis.thirdparty.api.client.FeignProperty;
import team.retum.jobis.thirdparty.api.client.InterestCompanyFeignClient;
import team.retum.jobis.thirdparty.api.client.dto.BusinessNumberResponse;
import team.retum.jobis.thirdparty.api.client.dto.InterestCompanyResponse;
import team.retum.jobis.thirdparty.api.exception.FeignBadRequestException;
import team.retum.jobis.thirdparty.api.exception.FeignServerErrorException;
import team.retum.jobis.thirdparty.api.exception.FeignUnauthorisedException;

import java.util.List;

@RequiredArgsConstructor
@Component
public class FeignClientAdapter implements FeignClientPort {

    private final FeignProperty feignProperty;
    private final BizNoFeignClient bizNoFeignClient;
    private final InterestCompanyFeignClient interestCompanyFeignClient;

    @Override
    public String getCompanyNameByBizNo(String businessNumber) {
        BusinessNumberResponse response = callApi(businessNumber);
        if (response.getTotalCount() == 0) {
            throw CompanyNotExistsException.EXCEPTION;
        }
        return response.getItems().get(0).getCompany();
    }

    @Override
    public boolean checkCompanyExistsByBizNo(String businessNumber) {
        return callApi(businessNumber).getTotalCount() != 0;
    }

    @Override
    public List<String> getMyInterestCompanyByMajorAndTech(List<String> major, List<String> tech) {
        String majors = (major == null ? "" : String.join(",", major));
        String techs = (tech == null ? "" : String.join(",", tech));

        InterestCompanyResponse response = interestCompanyFeignClient.getApi(majors, techs);

        return response.getRecommendedCompanies();
    }

    private BusinessNumberResponse callApi(String businessNumber) {
        BusinessNumberResponse response = bizNoFeignClient.getApi(
            feignProperty.getAccessKey(), 1, "N", businessNumber, "JSON"
        );

        if (response.getResultCode() < 0) {
            switch (response.getResultCode()) {
                case -1 -> throw FeignUnauthorisedException.EXCEPTION;
                case -2 -> throw FeignBadRequestException.EXCEPTION;
                default -> throw FeignServerErrorException.EXCEPTION;
            }
        }

        return response;
    }
}
