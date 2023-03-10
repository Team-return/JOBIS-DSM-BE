package com.example.jobis.domain.company.facade;

import com.example.jobis.domain.company.domain.Company;
import com.example.jobis.domain.company.domain.repository.CompanyJpaRepository;
import com.example.jobis.domain.company.exception.CompanyNotFoundException;
import com.example.jobis.infrastructure.feignClients.BizNoFeignClient;
import com.example.jobis.infrastructure.feignClients.FeignProperty;
import com.example.jobis.infrastructure.feignClients.dto.Items;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.UUID;

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

    public boolean companyExists(String businessNumber) {
        return companyJpaRepository.existsByBizNo(businessNumber);
    }

    public Company queryCompanyById(UUID id) {
        return companyJpaRepository.findById(id)
                .orElseThrow(() -> CompanyNotFoundException.EXCEPTION);
    }

    public Company getCurrentCompany() {
        String accountId = SecurityContextHolder.getContext().getAuthentication().getName();
        return companyJpaRepository.findByBizNo(accountId)
                .orElseThrow(()->CompanyNotFoundException.EXCEPTION);
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
