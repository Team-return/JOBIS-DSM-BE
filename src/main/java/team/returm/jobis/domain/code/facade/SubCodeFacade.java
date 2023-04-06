package team.returm.jobis.domain.code.facade;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import team.returm.jobis.domain.code.domain.SubCode;
import team.returm.jobis.domain.code.domain.repository.SubCodeJpaRepository;
import team.returm.jobis.domain.code.exception.CodeNotFoundException;

import java.util.List;

@RequiredArgsConstructor
@Component
public class SubCodeFacade {

    private final SubCodeJpaRepository subCodeJpaRepository;

    public List<SubCode> findAllCodeById(List<Long> requestCodes) {
        List<SubCode> subCodes = subCodeJpaRepository.querySubCodesByIdIn(requestCodes);
        if (subCodes.size() != requestCodes.size()) {
            throw CodeNotFoundException.EXCEPTION;
        }

        return subCodes;
    }
}
