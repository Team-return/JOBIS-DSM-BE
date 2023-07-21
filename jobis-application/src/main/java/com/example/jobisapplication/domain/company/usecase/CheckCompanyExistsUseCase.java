package com.example.jobisapplication.domain.company.usecase;

import com.example.jobisapplication.common.annotation.ReadOnlyUseCase;
import com.example.jobisapplication.common.spi.FeignClientPort;
import com.example.jobisapplication.domain.company.dto.response.CheckCompanyExistsResponse;
import com.example.jobisapplication.domain.company.spi.QueryCompanyPort;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@ReadOnlyUseCase
public class CheckCompanyExistsUseCase {

    private final FeignClientPort feignClientPort;
    private final QueryCompanyPort queryCompanyPort;

    public CheckCompanyExistsResponse execute(String businessNumber) {
        String companyName = feignClientPort.getCompanyNameByBizNo(businessNumber);
        boolean exists = queryCompanyPort.existsCompanyByBizNo(businessNumber);

        return CheckCompanyExistsResponse.builder()
                .companyName(companyName)
                .exists(exists)
                .build();
    }
}
