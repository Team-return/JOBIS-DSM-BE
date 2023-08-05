package team.retum.jobis.domain.recruitment.persistence.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import team.retum.jobis.domain.recruitment.exception.RecruitmentNotFoundException;
import team.retum.jobis.domain.recruitment.model.RecruitArea;
import team.retum.jobis.domain.recruitment.persistence.entity.RecruitAreaEntity;
import team.retum.jobis.domain.recruitment.persistence.entity.RecruitmentEntity;
import team.retum.jobis.domain.recruitment.persistence.repository.RecruitmentJpaRepository;

@RequiredArgsConstructor
@Component
public class RecruitAreaMapper {

    private final RecruitmentJpaRepository recruitmentJpaRepository;

    public RecruitAreaEntity toEntity(RecruitArea domain) {
        RecruitmentEntity recruitment = recruitmentJpaRepository.findById(domain.getRecruitmentId())
                .orElseThrow(() -> RecruitmentNotFoundException.EXCEPTION);

        return RecruitAreaEntity.builder()
                .id(domain.getId())
                .recruitmentEntity(recruitment)
                .hiredCount(domain.getHiredCount())
                .jobCodes(domain.getJobCodes())
                .majorTask(domain.getMajorTask())
                .build();
    }

    public RecruitArea toDomain(RecruitAreaEntity entity) {
        return RecruitArea.builder()
                .id(entity.getId())
                .recruitmentId(entity.getRecruitment().getId())
                .majorTask(entity.getMajorTask())
                .jobCodes(entity.getJobCodes())
                .majorTask(entity.getMajorTask())
                .build();
    }
}
