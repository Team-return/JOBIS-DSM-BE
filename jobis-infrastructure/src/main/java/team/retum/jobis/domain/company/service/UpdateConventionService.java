package team.retum.jobis.domain.company.service;

import lombok.RequiredArgsConstructor;
import team.retum.jobis.domain.company.persistence.entity.CompanyEntity;
import team.retum.jobis.domain.company.persistence.repository.CompanyRepository;
import com.example.jobisapplication.domain.company.exception.CompanyNotFoundException;
import team.retum.jobis.domain.company.presentation.dto.request.UpdateMouRequest;
import com.example.jobisapplication.common.annotation.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UpdateConventionService {

    private final CompanyRepository companyRepository;

    public void execute(UpdateMouRequest request) {
        List<CompanyEntity> companies = companyRepository.queryCompaniesByIdIn(request.getCompanyIds());

        if (companies.size() != request.getCompanyIds().size()) {
            throw CompanyNotFoundException.EXCEPTION;
        }

        companyRepository.saveAllCompanies(
                companies.stream()
                        .map(CompanyEntity::convertToMou)
                        .toList()
        );
    }
}
