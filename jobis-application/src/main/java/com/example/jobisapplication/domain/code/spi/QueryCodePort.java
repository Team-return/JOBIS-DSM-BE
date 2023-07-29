package com.example.jobisapplication.domain.code.spi;

import com.example.jobisapplication.domain.code.model.Code;
import com.example.jobisapplication.domain.code.model.CodeType;

import java.util.List;
import java.util.Optional;

public interface QueryCodePort {

    List<Code> queryCodesByKeywordAndType(String keyword, CodeType codeType, Long parentCode);

    Optional<Code> queryCodeById(Long codeId);

    List<Code> queryCodesByIdIn(List<Long> codes);
}