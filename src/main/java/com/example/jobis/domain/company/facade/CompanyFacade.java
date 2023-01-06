package com.example.jobis.domain.company.facade;

import com.example.jobis.domain.company.domain.Company;
import com.example.jobis.domain.company.domain.repository.CompanyRepository;
import com.example.jobis.domain.company.exception.CompanyNotFoundException;
import com.example.jobis.infrastructure.feignClients.BizNoFeignClient;
import com.example.jobis.infrastructure.feignClients.FeignProperty;
import com.example.jobis.infrastructure.feignClients.dto.BusinessNumberResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class CompanyFacade {

    private final BizNoFeignClient bizNoFeignClient;
    private final FeignProperty feignProperty;
    private final CompanyRepository companyRepository;

    public String getCompanyName(String businessNumber) {
        BusinessNumberResponse response = getApi(businessNumber);
        return response.getItems().get(0).getCompany();
    }

    public boolean checkCompany(String businessNumber) {
        BusinessNumberResponse response = getApi(businessNumber);
        return response.getItems().get(0).getBno().replace("-", "").equals(businessNumber);
    }

    public boolean companyExists(String businessNumber) {
        return companyRepository.existsByBusinessNumber(businessNumber);
    }

    public Company getCompany() {
        String accountId = SecurityContextHolder.getContext().getAuthentication().getName();
        return companyRepository.findByAccountId(accountId)
                .orElseThrow(() -> CompanyNotFoundException.EXCEPTION);
    }

    public Company getCompanyById(Long id) {
        return companyRepository.findById(id)
                .orElseThrow(() -> CompanyNotFoundException.EXCEPTION);
    }

    public Company getCompanyByBusinessNumber(String businessNumber) {
        return companyRepository.findByBusinessNumber(businessNumber)
                .orElseThrow(() -> CompanyNotFoundException.EXCEPTION);
    }

    public Company getCurrentCompany() {
        String accountId = SecurityContextHolder.getContext().getAuthentication().getName();
        return companyRepository.findByAccountId(accountId)
                .orElseThrow(()->CompanyNotFoundException.EXCEPTION);
    }

    private BusinessNumberResponse getApi(String businessNumber) {
        BusinessNumberResponse response = bizNoFeignClient.getApi(feignProperty.getAccessKey(),
                1, "N", businessNumber, "json");
        if (response.getItems() == null) {
            throw CompanyNotFoundException.EXCEPTION;
        }
        return response;
    }
}
