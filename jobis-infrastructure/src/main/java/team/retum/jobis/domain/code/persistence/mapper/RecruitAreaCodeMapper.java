package team.retum.jobis.domain.code.persistence.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import team.retum.jobis.domain.code.exception.CodeNotFoundException;
import team.retum.jobis.domain.code.model.RecruitAreaCode;
import team.retum.jobis.domain.code.persistence.entity.CodeEntity;
import team.retum.jobis.domain.code.persistence.entity.RecruitAreaCodeEntity;
import team.retum.jobis.domain.code.persistence.entity.RecruitAreaCodeId;
import team.retum.jobis.domain.code.persistence.repository.CodeJpaRepository;
import team.retum.jobis.domain.recruitment.exception.RecruitAreaNotFoundException;
import team.retum.jobis.domain.recruitment.persistence.entity.RecruitAreaEntity;
import team.retum.jobis.domain.recruitment.persistence.repository.RecruitAreaJpaRepository;

@RequiredArgsConstructor
@Component
public class RecruitAreaCodeMapper {

    private final RecruitAreaJpaRepository recruitAreaJpaRepository;
    private final CodeJpaRepository codeJpaRepository;

    public RecruitAreaCodeEntity toEntity(RecruitAreaCode domain) {
        RecruitAreaEntity recruitArea = recruitAreaJpaRepository.findById(domain.getRecruitAreaId())
                .orElseThrow(() -> RecruitAreaNotFoundException.EXCEPTION);

        CodeEntity code = codeJpaRepository.findById(domain.getCode())
                .orElseThrow(() -> CodeNotFoundException.EXCEPTION);

        return new RecruitAreaCodeEntity(
                new RecruitAreaCodeId(domain.getRecruitAreaId(), domain.getCode()),
                recruitArea,
                code,
                domain.getType()
        );
    }

    public RecruitAreaCode toDomain(RecruitAreaCodeEntity entity) {
        return RecruitAreaCode.builder()
                .recruitAreaId(entity.getRecruitArea().getId())
                .code(entity.getCode().getCode())
                .type(entity.getType())
                .build();
    }
}
