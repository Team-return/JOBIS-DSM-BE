package team.returm.jobis.domain.recruitment.facade;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import team.returm.jobis.domain.code.facade.CodeFacade;
import team.returm.jobis.domain.recruitment.domain.RecruitArea;
import team.returm.jobis.domain.recruitment.domain.repository.RecruitAreaJpaRepository;
import team.returm.jobis.domain.recruitment.exception.RecruitAreaNotFoundException;

@RequiredArgsConstructor
@Component
public class RecruitAreaFacade {

    private final RecruitAreaJpaRepository recruitAreaJpaRepository;
    private final CodeFacade codeFacade;

    public RecruitArea getRecruitAreaById(Long id) {
        return recruitAreaJpaRepository.findById(id)
                .orElseThrow(() -> RecruitAreaNotFoundException.EXCEPTION);
    }
}
