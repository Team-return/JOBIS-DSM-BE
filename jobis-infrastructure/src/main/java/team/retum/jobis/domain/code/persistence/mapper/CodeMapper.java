package team.retum.jobis.domain.code.persistence.mapper;

import team.retum.jobis.domain.code.exception.CodeNotFoundException;
import team.retum.jobis.domain.code.model.Code;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import team.retum.jobis.domain.code.persistence.entity.CodeEntity;
import team.retum.jobis.domain.code.persistence.repository.CodeJpaRepository;

@RequiredArgsConstructor
@Component
public class CodeMapper {

    private final CodeJpaRepository codeJpaRepository;

    public CodeEntity toEntity(Code domain) {
        CodeEntity code = codeJpaRepository.findById(domain.getParentCodeId())
                .orElseThrow(() -> CodeNotFoundException.EXCEPTION);

        return CodeEntity.builder()
                .codeType(domain.getCodeType())
                .keyword(domain.getKeyword())
                .jobType(domain.getJobType())
                .parentCodeEntity(code)
                .build();
    }

    public Code toDomain(CodeEntity entity) {
        return Code.builder()
                .id(entity.getId())
                .codeType(entity.getCodeType())
                .jobType(entity.getJobType())
                .parentCodeId(entity.getParentCode().getId())
                .build();
    }
}
