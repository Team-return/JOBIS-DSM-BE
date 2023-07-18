package team.retum.jobis.domain.recruitment.persistence.mapper;

import com.example.jobisapplication.domain.recruitment.exception.RecruitmentNotFoundException;
import com.example.jobisapplication.domain.recruitment.model.RecruitArea;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import team.retum.jobis.domain.recruitment.persistence.entity.RecruitAreaEntity;
import team.retum.jobis.domain.recruitment.persistence.entity.RecruitmentEntity;
import team.retum.jobis.domain.recruitment.persistence.repository.RecruitAreaJpaRepository;
import team.retum.jobis.domain.recruitment.persistence.repository.RecruitmentJpaRepository;

@RequiredArgsConstructor
@Component
public class RecruitAreaMapper {

    private final RecruitmentJpaRepository recruitmentJpaRepository;

    public RecruitAreaEntity toEntity(RecruitArea domain) {
        RecruitmentEntity recruitment = recruitmentJpaRepository.findById(domain.getRecruitmentId())
                .orElseThrow(() -> RecruitmentNotFoundException.EXCEPTION);

        return RecruitAreaEntity.builder()
                .recruitmentEntity(recruitment)
                .hiredCount(domain.getHiredCount())
                .jobCodes(domain.getJobCodes())
                .majorTask(domain.getMajorTask())
                .build();
    }

    public RecruitArea toDomain(RecruitAreaEntity entity) {
        return RecruitArea.builder()
                .id(entity.getId())
                .recruitmentId(entity.getRecruitmentEntity().getId())
                .majorTask(entity.getMajorTask())
                .jobCodes(entity.getJobCodes())
                .majorTask(entity.getMajorTask())
                .build();
    }
}
