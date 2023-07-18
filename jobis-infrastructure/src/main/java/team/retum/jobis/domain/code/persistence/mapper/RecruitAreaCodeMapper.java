package team.retum.jobis.domain.code.persistence.mapper;

import com.example.jobisapplication.domain.code.exception.CodeNotFoundException;
import com.example.jobisapplication.domain.code.model.RecruitAreaCode;
import com.example.jobisapplication.domain.recruitment.exception.RecruitAreaNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import team.retum.jobis.domain.code.persistence.entity.CodeEntity;
import team.retum.jobis.domain.code.persistence.entity.RecruitAreaCodeEntity;
import team.retum.jobis.domain.code.persistence.repository.CodeJpaRepository;
import team.retum.jobis.domain.code.persistence.repository.RecruitAreaCodeJpaRepository;
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

        CodeEntity code = codeJpaRepository.findById(domain.getCodeId())
                .orElseThrow(() -> CodeNotFoundException.EXCEPTION);

        return new RecruitAreaCodeEntity(recruitArea, code);
    }

    public RecruitAreaCode toDomain(RecruitAreaCodeEntity entity) {
        return RecruitAreaCode.builder()
                .recruitAreaId(entity.getRecruitAreaEntity().getId())
                .codeId(entity.getCodeEntity().getId())
                .build();
    }
}