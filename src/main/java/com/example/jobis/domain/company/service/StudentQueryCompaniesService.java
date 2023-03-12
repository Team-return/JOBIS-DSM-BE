package com.example.jobis.domain.company.service;

import com.example.jobis.domain.company.presentation.dto.response.QueryCompaniesResponse;
import com.example.jobis.domain.company.domain.repository.CompanyRepository;
import com.example.jobis.global.annotation.ReadOnlyService;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
@ReadOnlyService
public class StudentQueryCompaniesService {

    private final CompanyRepository companyRepository;

    public List<QueryCompaniesResponse> execute() {
        return companyRepository.findCompanyInfoList();
    }
}
