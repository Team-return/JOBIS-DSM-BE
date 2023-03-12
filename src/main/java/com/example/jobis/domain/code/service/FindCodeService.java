package com.example.jobis.domain.code.service;

import com.example.jobis.domain.code.domain.Code;
import com.example.jobis.domain.code.domain.enums.CodeType;
import com.example.jobis.domain.code.domain.repository.CodeRepository;
import com.example.jobis.domain.code.presentation.dto.response.CodeResponse;
import com.example.jobis.global.annotation.ReadOnlyService;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
@ReadOnlyService
public class FindCodeService {
    private final CodeRepository codeRepository;

    public List<CodeResponse> execute(String keyword, CodeType type) {
        return codeRepository.findByKeywordContainingAndCodeType(keyword, type)
                .stream()
                .map(Code::to)
                .toList();
    }
}
