package team.returm.jobis.domain.code.facade;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import team.returm.jobis.domain.code.domain.Code;
import team.returm.jobis.domain.code.domain.RecruitAreaCode;
import team.returm.jobis.domain.code.domain.repository.CodeJpaRepository;
import team.returm.jobis.domain.code.exception.CodeNotFoundException;
import team.returm.jobis.domain.recruitment.domain.RecruitArea;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CodeFacade {
    private final CodeJpaRepository codeJpaRepository;

    public List<Code> queryCodesByIdIn(List<Long> requestCodes) {
        List<Code> codes = codeJpaRepository.findCodesByIdIn(requestCodes);
        if (codes.size() != requestCodes.size()) {
            throw CodeNotFoundException.EXCEPTION;
        }

        return codes;
    }

    public List<Code> queryCodeByKeywordIn(List<String> codeKeywords) {
        if (codeKeywords==null) {
           return null;
        }
        List<Code> codes = codeJpaRepository.findCodesByKeywordIn(codeKeywords);
        if (codes.size() != codeKeywords.size()) {
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
                                code
                        )
                )
                .toList();
    }
}
