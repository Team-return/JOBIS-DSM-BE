package team.retum.jobis.domain.company.service;

import lombok.RequiredArgsConstructor;
import team.retum.jobis.domain.company.persistence.enums.CompanyType;
import team.retum.jobis.domain.company.persistence.repository.CompanyRepository;
import team.retum.jobis.domain.company.presentation.dto.response.TeacherQueryEmployCompaniesResponse;
import team.retum.jobis.global.annotation.ReadOnlyService;

@RequiredArgsConstructor
@ReadOnlyService
public class TeacherQueryEmployCompaniesService {

    private final CompanyRepository companyRepository;

    public TeacherQueryEmployCompaniesResponse execute(String companyName, CompanyType type, Integer year) {
        return new TeacherQueryEmployCompaniesResponse(
                companyRepository.queryEmployCompanies(companyName, type, year)
        );
    }
}
