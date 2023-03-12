package com.example.jobis.domain.company.service;

import com.example.jobis.domain.company.controller.dto.response.QueryCompaniesResponse;
import com.example.jobis.domain.company.domain.repository.CompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class StudentQueryCompaniesService {

    private final CompanyRepository companyRepository;

    @Transactional(readOnly = true)
    public List<QueryCompaniesResponse> execute() {
        return companyRepository.findCompanyInfoList();
    }
}
