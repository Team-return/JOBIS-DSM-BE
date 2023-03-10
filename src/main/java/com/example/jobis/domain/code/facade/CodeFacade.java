package com.example.jobis.domain.code.facade;

import com.example.jobis.domain.code.domain.Code;
import com.example.jobis.domain.code.domain.RecruitAreaCode;
import com.example.jobis.domain.code.domain.repository.CodeRepository;
import com.example.jobis.domain.code.exception.CodeNotFoundException;
import com.example.jobis.domain.recruitment.domain.RecruitArea;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CodeFacade {
    private final CodeRepository codeRepository;

    public List<Code> findAllCodeById(List<Long> requestCodes) {
        List<Code> codes = codeRepository.findAllById(requestCodes);
        if(codes.size() != requestCodes.size()) {
            throw CodeNotFoundException.EXCEPTION;
        }

        return codes;
    }

    public Code findCodeById(Long id) {
        return codeRepository.findById(id)
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
