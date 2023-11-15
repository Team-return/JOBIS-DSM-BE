package team.retum.jobis.domain.code.persistence.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import team.retum.jobis.domain.code.exception.CodeNotFoundException;
import team.retum.jobis.domain.code.model.Code;
import team.retum.jobis.domain.code.persistence.entity.CodeEntity;
import team.retum.jobis.domain.code.persistence.repository.CodeJpaRepository;

@RequiredArgsConstructor
@Component
public class CodeMapper {

    private final CodeJpaRepository codeJpaRepository;

    public CodeEntity toEntity(Code domain) {
        CodeEntity code = domain.getParentCodeId() == null ?
                null : codeJpaRepository.findById(domain.getParentCodeId())
                .orElseThrow(() -> CodeNotFoundException.EXCEPTION);

        return CodeEntity.builder()
                .id(domain.getId())
                .codeType(domain.getCodeType())
                .keyword(domain.getKeyword())
                .jobType(domain.getJobType())
                .parentCodeEntity(code)
                .isUsed(domain.isUsed())
                .build();
    }

    public Code toDomain(CodeEntity entity) {
        return Code.builder()
                .id(entity.getId())
                .codeType(entity.getCodeType())
                .jobType(entity.getJobType())
                .keyword(entity.getKeyword())
                .parentCodeId(entity.getParentCode() == null ? null : entity.getParentCode().getId())
                .isUsed(entity.isUsed())
                .build();
    }
}
