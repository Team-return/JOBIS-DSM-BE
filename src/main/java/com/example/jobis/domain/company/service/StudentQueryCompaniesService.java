package com.example.jobis.domain.company.service;

import com.example.jobis.domain.company.presentation.dto.response.StudentQueryCompaniesResponse;
import com.example.jobis.domain.company.domain.repository.CompanyRepository;
import com.example.jobis.global.annotation.ReadOnlyService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@ReadOnlyService
public class StudentQueryCompaniesService {

    private final CompanyRepository companyRepository;

    public StudentQueryCompaniesResponse execute() {
        return new StudentQueryCompaniesResponse(companyRepository.findCompanyInfoList());
    }
}
