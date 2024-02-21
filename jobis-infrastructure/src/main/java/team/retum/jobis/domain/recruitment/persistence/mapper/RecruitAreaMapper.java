package team.retum.jobis.domain.recruitment.persistence.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import team.retum.jobis.domain.code.model.CodeType;
import team.retum.jobis.domain.code.persistence.entity.CodeEntity;
import team.retum.jobis.domain.code.persistence.entity.RecruitAreaCodeEntity;
import team.retum.jobis.domain.code.persistence.repository.RecruitAreaCodeJpaRepository;
import team.retum.jobis.domain.recruitment.exception.RecruitmentNotFoundException;
import team.retum.jobis.domain.recruitment.model.RecruitArea;
import team.retum.jobis.domain.recruitment.persistence.entity.RecruitAreaEntity;
import team.retum.jobis.domain.recruitment.persistence.entity.RecruitmentEntity;
import team.retum.jobis.domain.recruitment.persistence.repository.RecruitmentJpaRepository;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class RecruitAreaMapper {

    private final RecruitmentJpaRepository recruitmentJpaRepository;
    private final RecruitAreaCodeJpaRepository recruitAreaCodeJpaRepository;

    public RecruitAreaEntity toEntity(RecruitArea domain) {
        RecruitmentEntity recruitment = recruitmentJpaRepository.findById(domain.getRecruitmentId())
                .orElseThrow(() -> RecruitmentNotFoundException.EXCEPTION);

        return RecruitAreaEntity.builder()
                .id(domain.getId())
                .recruitmentEntity(recruitment)
                .hiredCount(domain.getHiredCount())
                .majorTask(domain.getMajorTask())
                .preferentialTreatment(domain.getPreferentialTreatment())
                .build();
    }

    public RecruitArea toDomain(RecruitAreaEntity entity) {
        Map<CodeType, List<Long>> codes = recruitAreaCodeJpaRepository.findByRecruitAreaId(entity.getId()).stream()
                .map(RecruitAreaCodeEntity::getCode)
                .collect(Collectors.groupingBy(
                        CodeEntity::getType,
                        Collectors.mapping(CodeEntity::getCode, Collectors.toList()))
                );


        return RecruitArea.builder()
                .id(entity.getId())
                .recruitmentId(entity.getRecruitment().getId())
                .majorTask(entity.getMajorTask())
                .preferentialTreatment(entity.getPreferentialTreatment())
                .codes(codes)
                .build();
    }
}
