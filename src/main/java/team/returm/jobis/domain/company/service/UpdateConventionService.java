package team.returm.jobis.domain.company.service;

import lombok.RequiredArgsConstructor;
import team.returm.jobis.domain.company.domain.Company;
import team.returm.jobis.domain.company.domain.repository.CompanyRepository;
import team.returm.jobis.domain.company.exception.CompanyNotFoundException;
import team.returm.jobis.domain.company.presentation.dto.request.UpdateMouRequest;
import team.returm.jobis.global.annotation.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UpdateConventionService {

    private final CompanyRepository companyRepository;

    public void execute(UpdateMouRequest request) {
        List<Company> companies = companyRepository.queryCompaniesByIdIn(request.getCompanyIds());

        if (companies.size() != request.getCompanyIds().size()) {
            throw CompanyNotFoundException.EXCEPTION;
        }

        companyRepository.saveAllCompanies(
                companies.stream()
                        .map(Company::convertToMou)
                        .toList()
        );
    }
}
