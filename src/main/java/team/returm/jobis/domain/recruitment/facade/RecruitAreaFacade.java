package team.returm.jobis.domain.recruitment.facade;

import team.returm.jobis.domain.recruitment.domain.RecruitArea;
import team.returm.jobis.domain.recruitment.domain.repository.RecruitAreaJpaRepository;
import team.returm.jobis.domain.recruitment.exception.RecruitAreaNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@RequiredArgsConstructor
@Component
public class RecruitAreaFacade {

    private final RecruitAreaJpaRepository recruitAreaJpaRepository;

    public RecruitArea getRecruitAreaById(UUID id) {
        return recruitAreaJpaRepository.findById(id)
                .orElseThrow(() -> RecruitAreaNotFoundException.EXCEPTION);
    }
}
