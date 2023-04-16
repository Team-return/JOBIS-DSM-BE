package team.returm.jobis.domain.company.service;

import lombok.RequiredArgsConstructor;
import team.returm.jobis.domain.company.domain.enums.CompanyType;
import team.returm.jobis.domain.company.domain.repository.CompanyRepository;
import team.returm.jobis.domain.company.presentation.dto.response.TeacherQueryEmployCompaniesResponse;
import team.returm.jobis.global.annotation.ReadOnlyService;

@RequiredArgsConstructor
@ReadOnlyService
public class TeacherQueryEmployCompaniesService {

    private final CompanyRepository companyRepository;

    public TeacherQueryEmployCompaniesResponse execute(String companyName, CompanyType type) {
        return new TeacherQueryEmployCompaniesResponse(
                companyRepository.queryEmployCompanies(companyName, type)
        );
    }
}
