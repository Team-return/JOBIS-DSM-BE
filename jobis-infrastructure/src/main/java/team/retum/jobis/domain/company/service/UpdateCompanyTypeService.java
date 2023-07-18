package team.retum.jobis.domain.company.service;

import lombok.RequiredArgsConstructor;
import team.retum.jobis.domain.company.persistence.entity.CompanyEntity;
import team.retum.jobis.domain.company.persistence.repository.CompanyRepository;
import team.retum.jobis.domain.company.exception.CompanyNotFoundException;
import team.retum.jobis.domain.company.presentation.dto.request.UpdateCompanyTypeRequest;
import com.example.jobisapplication.common.annotation.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UpdateCompanyTypeService {

    private final CompanyRepository companyRepository;

    public void execute(UpdateCompanyTypeRequest request) {

        List<CompanyEntity> companies = companyRepository.queryCompaniesByIdIn(request.getCompanyIds());

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
