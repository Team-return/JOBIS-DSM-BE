package team.retum.jobis.domain.company.service;

import lombok.RequiredArgsConstructor;
import team.retum.jobis.domain.company.domain.Company;
import team.retum.jobis.domain.company.domain.repository.CompanyRepository;
import team.retum.jobis.domain.company.exception.CompanyNotFoundException;
import team.retum.jobis.domain.company.presentation.dto.request.UpdateCompanyTypeRequest;
import team.retum.jobis.global.annotation.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UpdateCompanyTypeService {

    private final CompanyRepository companyRepository;

    public void execute(UpdateCompanyTypeRequest request) {

        List<Company> companies = companyRepository.queryCompaniesByIdIn(request.getCompanyIds());

        if (companies.size() != request.getCompanyIds().size()) {
            throw CompanyNotFoundException.EXCEPTION;
        }

        companyRepository.saveAllCompanies(
                companies.stream()
                        .map(company -> company.changeCompanyType(request.getCompanyType()))
                        .toList()
        );
    }
}
