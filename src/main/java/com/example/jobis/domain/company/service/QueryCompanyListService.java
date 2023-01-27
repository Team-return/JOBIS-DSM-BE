package com.example.jobis.domain.company.service;

import com.example.jobis.domain.company.domain.repository.CompanyJpaRepository;
import com.example.jobis.domain.company.controller.dto.response.CompanyListResponse;
import com.example.jobis.domain.company.controller.dto.response.CompanyResponse;
import com.example.jobis.domain.company.domain.repository.CompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class QueryCompanyListService {

    private final CompanyRepository companyRepository;

    @Transactional(readOnly = true)
    public CompanyListResponse execute() {

        List<CompanyResponse> companyList = companyRepository.findCompanyInfoList();

        return new CompanyListResponse(companyList);
    }
}
