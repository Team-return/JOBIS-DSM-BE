package com.example.jobis.domain.code.facade;

import com.example.jobis.domain.code.domain.Code;
import com.example.jobis.domain.code.domain.repository.CodeRepository;
import com.example.jobis.domain.code.exception.InvalidCodeException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class CodeFacade {
    private final CodeRepository codeRepository;

    public List<Code> findAllCodeById(List<Long> codeIdList) {
        List<Code> codeList = new ArrayList<>();
        codeIdList.forEach(
                c -> codeList.add(codeRepository.findById(c)
                        .orElseThrow(() -> InvalidCodeException.EXCEPTION))
        );
        return codeList;
    }
}
