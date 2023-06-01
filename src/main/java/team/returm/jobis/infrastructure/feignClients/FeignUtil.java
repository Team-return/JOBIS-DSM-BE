package team.returm.jobis.infrastructure.feignClients;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import team.returm.jobis.infrastructure.feignClients.dto.BusinessNumberResponse;
import team.returm.jobis.infrastructure.feignClients.exception.FeignBadRequestException;
import team.returm.jobis.infrastructure.feignClients.exception.FeignServerErrorException;
import team.returm.jobis.infrastructure.feignClients.exception.FeignUnauthorisedException;

@RequiredArgsConstructor
@Component
public class FeignUtil {

    private final FeignProperty feignProperty;
    private final BizNoFeignClient bizNoFeignClient;

    public String getCompanyName(String businessNumber) {
        return callApi(businessNumber).getItems().get(0).getCompany();
    }

    public boolean checkCompanyExists(String businessNumber) {
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
