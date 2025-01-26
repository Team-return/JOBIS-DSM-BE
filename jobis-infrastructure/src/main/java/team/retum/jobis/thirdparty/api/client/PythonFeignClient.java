package team.retum.jobis.thirdparty.api.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import team.retum.jobis.thirdparty.api.client.dto.InterestRecruitmentResponse;

@FeignClient(name = "Python", url = "http://3.37.88.98:8314/recommend")
public interface PythonFeignClient {

    @GetMapping
    InterestRecruitmentResponse getApi(@RequestParam("major") String major, @RequestParam("tech") String tech);
}
