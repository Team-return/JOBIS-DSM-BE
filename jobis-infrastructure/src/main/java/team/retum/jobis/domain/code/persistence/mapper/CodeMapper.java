package team.retum.jobis.domain.code.persistence.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import team.retum.jobis.domain.code.model.Code;
import team.retum.jobis.domain.code.persistence.entity.CodeEntity;
import team.retum.jobis.domain.code.persistence.repository.CodeJpaRepository;

@RequiredArgsConstructor
@Component
public class CodeMapper {

    private final CodeJpaRepository codeJpaRepository;

    public CodeEntity toEntity(Code domain) {
        CodeEntity code = domain.getParentCodeId() == null
            ? null : codeJpaRepository.getReferenceById(domain.getParentCodeId());

        return CodeEntity.builder()
            .code(domain.getId())
            .codeType(domain.getCodeType())
            .keyword(domain.getKeyword())
            .jobType(domain.getJobType())
            .isPublic(domain.isPublic())
            .parentCodeEntity(code)
            .build();
    }

    public Code toDomain(CodeEntity entity) {
        return Code.builder()
            .id(entity.getCode())
            .codeType(entity.getType())
            .jobType(entity.getJobType())
            .keyword(entity.getKeyword())
            .isPublic(entity.isPublic())
            .parentCodeId(entity.getParentCode() == null ? null : entity.getParentCode().getCode())
            .build();
    }
}
