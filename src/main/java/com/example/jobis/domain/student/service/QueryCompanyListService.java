package com.example.jobis.domain.student.service;

import com.example.jobis.domain.company.domain.repository.CompanyRepository;
import com.example.jobis.domain.student.controller.dto.response.CompanyListResponse;
import com.example.jobis.domain.student.controller.dto.response.CompanyResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class QueryCompanyListService {

    private final CompanyRepository companyRepository;

    @Transactional(readOnly = true)
    public CompanyListResponse execute() {

        List<CompanyResponse> companyList = companyRepository.findAll().stream()
                .map(CompanyResponse::of)
                .collect(Collectors.toList());

        return new CompanyListResponse(companyList);
    }
}
