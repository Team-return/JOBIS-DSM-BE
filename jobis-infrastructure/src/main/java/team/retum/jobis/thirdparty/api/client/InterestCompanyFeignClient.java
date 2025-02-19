package team.retum.jobis.thirdparty.api.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import team.retum.jobis.thirdparty.api.client.dto.InterestCompanyResponse;

@FeignClient(name = "Interest", url = "${interest.url}")
public interface InterestCompanyFeignClient {

    @GetMapping
    InterestCompanyResponse getApi(@RequestParam("major") String major, @RequestParam("tech") String tech);
}
