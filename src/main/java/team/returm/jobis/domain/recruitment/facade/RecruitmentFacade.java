package team.returm.jobis.domain.recruitment.facade;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import team.returm.jobis.domain.recruitment.domain.Recruitment;
import team.returm.jobis.domain.recruitment.domain.repository.RecruitmentJpaRepository;
import team.returm.jobis.domain.recruitment.exception.RecruitmentNotFoundException;

@RequiredArgsConstructor
@Component
public class RecruitmentFacade {

    private final RecruitmentJpaRepository recruitmentJpaRepository;

    public Recruitment queryRecruitmentById(Long id) {
        return recruitmentJpaRepository.findById(id)
                .orElseThrow(() -> RecruitmentNotFoundException.EXCEPTION);
    }
}
