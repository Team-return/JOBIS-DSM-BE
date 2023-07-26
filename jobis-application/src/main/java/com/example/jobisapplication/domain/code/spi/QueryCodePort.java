package com.example.jobisapplication.domain.code.spi;

import com.example.jobisapplication.domain.code.model.Code;
import com.example.jobisapplication.domain.code.model.CodeType;

import java.util.List;

public interface QueryCodePort {

    List<Code> queryCodesByKeywordAndType(String keyword, CodeType codeType, Long parentCode);

    Code queryCodeById(Long codeId);

    List<Code> queryCodesByIdIn(List<Long> codes);
}
