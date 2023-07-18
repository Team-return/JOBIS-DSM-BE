package team.retum.jobis.domain.code.facade;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import team.retum.jobis.domain.code.persistence.entity.CodeEntity;
import team.retum.jobis.domain.code.persistence.repository.CodeJpaRepository;
import team.retum.jobis.domain.code.exception.CodeNotFoundException;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CodeFacade {

    private final CodeJpaRepository codeJpaRepository;

    public List<CodeEntity> queryCodesByIdIn(List<Long> requestCodes) {
        if (requestCodes == null || requestCodes.size() == 0) {
            return null;
        }
        List<CodeEntity> codeEntities = codeJpaRepository.findCodesByIdIn(requestCodes);
        if (codeEntities.size() != requestCodes.size()) {
            throw CodeNotFoundException.EXCEPTION;
        }

        return codeEntities;
    }

    public CodeEntity findCodeById(Long id) {
        return codeJpaRepository.findById(id)
                .orElseThrow(() -> CodeNotFoundException.EXCEPTION);
    }
}
