package com.example.jobis.domain.company.facade;

import com.example.jobis.domain.company.domain.Company;
import com.example.jobis.domain.company.domain.repository.CompanyRepository;
import com.example.jobis.domain.company.exception.CompanyNotFoundException;
import com.example.jobis.domain.user.domain.User;
import com.example.jobis.infrastructure.resttemplate.dto.response.BusinessNumberResponse;
import com.example.jobis.infrastructure.resttemplate.facade.RestTemplateFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class CompanyFacade {

    private final RestTemplateFacade restTemplateFacade;
    private final CompanyRepository companyRepository;

    public String getCompanyName(String businessNumber) {
        BusinessNumberResponse response = restTemplateFacade.getApi(businessNumber);
        return response.getBody().getItems().getItem().getCompany();
    }

    public boolean checkCompany(String businessNumber) {
        BusinessNumberResponse response = restTemplateFacade.getApi(businessNumber);
        return response.getBody().getItems().getItem().getBno().replace("-", "").equals(businessNumber);
    }

    public boolean companyExists(String businessNumber) {
        return companyRepository.existsByBusinessNumber(businessNumber);
    }

    public Company getCompany() {
        String accountId = SecurityContextHolder.getContext().getAuthentication().getName();
        return companyRepository.findByAccountId(accountId)
                .orElseThrow(() -> CompanyNotFoundException.EXCEPTION);
    }

    public Company getCompanyByBusinessNumber(String businessNumber) {
        return companyRepository.findByBusinessNumber(businessNumber)
                .orElseThrow(() -> CompanyNotFoundException.EXCEPTION);
    }
}
