package team.returm.jobis.domain.recruitment.facade;

import team.returm.jobis.domain.company.domain.Company;
import team.returm.jobis.domain.recruitment.domain.Recruitment;
import team.returm.jobis.domain.recruitment.domain.repository.RecruitmentJpaRepository;
import team.returm.jobis.domain.recruitment.exception.RecruitNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@RequiredArgsConstructor
@Component
public class RecruitFacade {

    private final RecruitmentJpaRepository recruitmentJpaRepository;

    public Recruitment queryRecruitmentById(UUID id) {
        return recruitmentJpaRepository.findById(id)
                .orElseThrow(() -> RecruitNotFoundException.EXCEPTION);
    }

    public Recruitment getLatestRecruitByCompany(Company company) {
        return company.getRecruitmentList().get(company.getRecruitmentList().size() - 1);
    }
}
