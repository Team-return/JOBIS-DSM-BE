package team.retum.jobis.domain.recruitment.persistence.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import team.retum.jobis.domain.code.model.CodeType;
import team.retum.jobis.domain.code.persistence.entity.RecruitAreaCodeEntity;
import team.retum.jobis.domain.code.persistence.entity.RecruitAreaCodeId;
import team.retum.jobis.domain.code.persistence.repository.CodeJpaRepository;
import team.retum.jobis.domain.recruitment.model.RecruitArea;
import team.retum.jobis.domain.recruitment.persistence.entity.RecruitAreaEntity;
import team.retum.jobis.domain.recruitment.persistence.entity.RecruitmentEntity;
import team.retum.jobis.domain.recruitment.persistence.repository.RecruitmentJpaRepository;

@RequiredArgsConstructor
@Component
public class RecruitAreaMapper {

    private final RecruitmentJpaRepository recruitmentJpaRepository;
    private final CodeJpaRepository codeJpaRepository;

    public RecruitAreaEntity toEntity(RecruitArea domain) {
        RecruitmentEntity recruitment = recruitmentJpaRepository.getReferenceById(domain.getRecruitmentId());
        RecruitAreaEntity recruitArea = RecruitAreaEntity.builder()
            .id(domain.getId())
            .recruitmentEntity(recruitment)
            .hiredCount(domain.getHiredCount())
            .majorTask(domain.getMajorTask())
            .preferentialTreatment(domain.getPreferentialTreatment())
            .build();

        if (domain.getCodes() != null) {
            domain.getCodes().forEach((type, codes) -> codes.forEach(code ->
                recruitArea.addRecruitAreaCode(buildRecruitAreaCodeEntity(recruitArea, code, type))
            ));
        }

        return recruitArea;
    }

    private RecruitAreaCodeEntity buildRecruitAreaCodeEntity(RecruitAreaEntity recruitArea, Long code, CodeType type) {
        return new RecruitAreaCodeEntity(
            new RecruitAreaCodeId(recruitArea.getId(), code),
            recruitArea,
            codeJpaRepository.getReferenceById(code),
            type
        );
    }


    public RecruitArea toDomain(RecruitAreaEntity entity) {
        return RecruitArea.builder()
            .id(entity.getId())
            .hiredCount(entity.getHiredCount())
            .recruitmentId(entity.getRecruitment().getId())
            .majorTask(entity.getMajorTask())
            .preferentialTreatment(entity.getPreferentialTreatment())
            .build();
    }
}
