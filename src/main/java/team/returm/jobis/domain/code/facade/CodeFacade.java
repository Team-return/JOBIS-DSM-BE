package team.returm.jobis.domain.code.facade;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import team.returm.jobis.domain.code.domain.Code;
import team.returm.jobis.domain.code.domain.RecruitAreaCode;
import team.returm.jobis.domain.code.domain.repository.CodeJpaRepository;
import team.returm.jobis.domain.code.exception.CodeNotFoundException;
import team.returm.jobis.domain.recruitment.domain.RecruitArea;

@Component
@RequiredArgsConstructor
public class CodeFacade {
    private final CodeJpaRepository codeJpaRepository;

    public List<Code> findAllCodeById(List<Long> requestCodes) {
        List<Code> codes = codeJpaRepository.findAllById(requestCodes);
        if (codes.size() != requestCodes.size()) {
            throw CodeNotFoundException.EXCEPTION;
        }

        return codes;
    }

    public Code findCodeById(Long id) {
        return codeJpaRepository.findById(id)
                .orElseThrow(() -> CodeNotFoundException.EXCEPTION);
    }

    public List<RecruitAreaCode> generateRecruitAreaCode(RecruitArea recruitArea, List<Code> codes) {
        return codes.stream()
                .map(code ->
                        new RecruitAreaCode(
                                recruitArea,
                                code.getKeyword(),
                                code.getCodeType()
                        )
                ).toList();
    }
}
