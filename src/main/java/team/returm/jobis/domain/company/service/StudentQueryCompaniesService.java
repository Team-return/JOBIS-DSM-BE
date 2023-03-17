package team.returm.jobis.domain.company.service;

import team.returm.jobis.domain.company.presentation.dto.response.StudentQueryCompaniesResponse;
import team.returm.jobis.domain.company.domain.repository.CompanyRepository;
import team.returm.jobis.global.annotation.ReadOnlyService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@ReadOnlyService
public class StudentQueryCompaniesService {

    private final CompanyRepository companyRepository;

    public StudentQueryCompaniesResponse execute() {
        return new StudentQueryCompaniesResponse(companyRepository.findCompanyInfoList());
    }
}