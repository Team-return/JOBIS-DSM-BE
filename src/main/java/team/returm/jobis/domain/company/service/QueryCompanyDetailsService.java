package team.returm.jobis.domain.company.service;

import team.returm.jobis.domain.company.domain.repository.CompanyRepository;
import team.returm.jobis.domain.company.presentation.dto.response.QueryCompanyDetailsResponse;
import team.returm.jobis.global.annotation.ReadOnlyService;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@RequiredArgsConstructor
@ReadOnlyService
public class QueryCompanyDetailsService {

    private final CompanyRepository companyRepository;

    public QueryCompanyDetailsResponse execute(UUID companyId) {
        return companyRepository.queryCompanyDetails(companyId);
    }

}
