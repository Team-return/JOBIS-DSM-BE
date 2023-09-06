package team.retum.jobis.thirdparty.api;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import team.retum.jobis.common.spi.FeignClientPort;
import team.retum.jobis.thirdparty.api.client.BizNoFeignClient;
import team.retum.jobis.thirdparty.api.client.FeignProperty;
import team.retum.jobis.thirdparty.api.client.dto.BusinessNumberResponse;
import team.retum.jobis.thirdparty.api.exception.FeignBadRequestException;
import team.retum.jobis.thirdparty.api.exception.FeignServerErrorException;
import team.retum.jobis.thirdparty.api.exception.FeignUnauthorisedException;

@RequiredArgsConstructor
@Component
public class FeignClientAdapter implements FeignClientPort {

    private final FeignProperty feignProperty;
    private final BizNoFeignClient bizNoFeignClient;

    @Override
    public String getCompanyNameByBizNo(String businessNumber) {
        BusinessNumberResponse response = callApi(businessNumber);
        if (response.getTotalCount() == 0) {
            return "존재하지 않는 기업";
        }
        return response.getItems().get(0).getCompany();
    }

    @Override
    public boolean checkCompanyExistsByBizNo(String businessNumber) {
        return callApi(businessNumber).getTotalCount() != 0;
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
