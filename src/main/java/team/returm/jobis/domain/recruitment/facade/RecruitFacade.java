package team.returm.jobis.domain.recruitment.facade;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import team.returm.jobis.domain.company.domain.Company;
import team.returm.jobis.domain.recruitment.domain.Recruitment;
import team.returm.jobis.domain.recruitment.domain.repository.RecruitmentJpaRepository;
import team.returm.jobis.domain.recruitment.exception.RecruitmentNotFoundException;
import team.returm.jobis.domain.recruitment.presentation.dto.request.RecruitAreaRequest;

@RequiredArgsConstructor
@Component
public class RecruitFacade {

    private final RecruitmentJpaRepository recruitmentJpaRepository;

    public Recruitment queryRecruitmentById(Long id) {
        return recruitmentJpaRepository.findById(id)
                .orElseThrow(() -> RecruitmentNotFoundException.EXCEPTION);
    }

    public Recruitment getLatestRecruitByCompany(Company company) {
        return company.getRecruitmentList().get(company.getRecruitmentList().size() - 1);
    }

    public void generateRecruitArea(RecruitAreaRequest request) {

    }
}
