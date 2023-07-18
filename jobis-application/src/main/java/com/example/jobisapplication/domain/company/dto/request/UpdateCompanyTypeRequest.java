package com.example.jobisapplication.domain.company.dto.request;

import com.example.jobisapplication.domain.company.model.CompanyType;
import lombok.Getter;

import java.util.List;

@Getter
public class UpdateCompanyTypeRequest {

    private List<Long> companyIds;

    private CompanyType companyType;
}
