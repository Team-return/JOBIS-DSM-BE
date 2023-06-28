package team.retum.jobis.domain.code.facade;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import team.retum.jobis.domain.code.domain.Code;
import team.retum.jobis.domain.code.domain.repository.CodeJpaRepository;
import team.retum.jobis.domain.code.exception.CodeNotFoundException;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CodeFacade {

    private final CodeJpaRepository codeJpaRepository;

    public List<Code> queryCodesByIdIn(List<Long> requestCodes) {
        if (requestCodes == null || requestCodes.size() == 0) {
            return null;
        }
        List<Code> codes = codeJpaRepository.findCodesByIdIn(requestCodes);
        if (codes.size() != requestCodes.size()) {
            throw CodeNotFoundException.EXCEPTION;
        }

        return codes;
    }

    public Code findCodeById(Long id) {
        return codeJpaRepository.findById(id)
                .orElseThrow(() -> CodeNotFoundException.EXCEPTION);
    }
}
