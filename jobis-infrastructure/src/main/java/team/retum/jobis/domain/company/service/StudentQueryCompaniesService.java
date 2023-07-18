package team.retum.jobis.domain.company.service;

import lombok.RequiredArgsConstructor;
import team.retum.jobis.domain.company.persistence.repository.CompanyRepository;
import team.retum.jobis.domain.company.presentation.dto.CompanyFilter;
import team.retum.jobis.domain.company.presentation.dto.response.StudentQueryCompaniesResponse;
import com.example.jobisapplication.common.annotation.ReadOnlyService;

@RequiredArgsConstructor
@ReadOnlyService
public class StudentQueryCompaniesService {

    private final CompanyRepository companyRepository;

    public StudentQueryCompaniesResponse execute(Long page, String name) {
        CompanyFilter filter = CompanyFilter.builder()
                .name(name)
                .page(page)
                .build();

        return new StudentQueryCompaniesResponse(
                companyRepository.queryCompanyVoList(filter)
        );
    }
}
