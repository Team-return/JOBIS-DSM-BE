package com.example.jobis.domain.code.service;

import com.example.jobis.domain.code.domain.Code;
import com.example.jobis.domain.code.domain.enums.CodeType;
import com.example.jobis.domain.code.domain.repository.CodeRepository;
import com.example.jobis.domain.code.presentation.dto.response.CodeResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FindCodeService {
    private final CodeRepository codeRepository;

    @Transactional(readOnly = true)
    public List<CodeResponse> execute(String keyword, CodeType type) {
        return codeRepository.findByKeywordContainingAndCodeType(keyword, type)
                .stream()
                .map(Code::to)
                .toList();
    }
}
