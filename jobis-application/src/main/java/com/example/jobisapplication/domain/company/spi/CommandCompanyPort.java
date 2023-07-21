package com.example.jobisapplication.domain.company.spi;

import com.example.jobisapplication.domain.company.model.Company;
import com.example.jobisapplication.domain.company.model.CompanyAttachment;

import java.util.List;

public interface CommandCompanyPort {

    Company saveCompany(Company company);

    void saveAllCompanies(List<Company> companies);

    void saveAllCompanyAttachment(List<CompanyAttachment> companyAttachmentEntities);
}
