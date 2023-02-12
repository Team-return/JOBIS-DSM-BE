package com.example.jobis.domain.code.facade;

import com.example.jobis.domain.code.domain.Code;
import com.example.jobis.domain.code.domain.RecruitAreaCode;
import com.example.jobis.domain.code.domain.enums.CodeType;
import com.example.jobis.domain.code.domain.repository.CodeRepository;
import com.example.jobis.domain.code.exception.CodeNotFoundException;
import com.example.jobis.domain.code.exception.InvalidCodeException;
import com.example.jobis.domain.recruit.domain.RecruitArea;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class CodeFacade {
    private final CodeRepository codeRepository;

    public List<Code> findAllCodeById(List<Long> codeIdList) {
        return codeRepository.findAllById(codeIdList);
    }

    public Code findCodeById(Long id) {
        return codeRepository.findById(id)
                .orElseThrow(() -> CodeNotFoundException.EXCEPTION);
    }
}
