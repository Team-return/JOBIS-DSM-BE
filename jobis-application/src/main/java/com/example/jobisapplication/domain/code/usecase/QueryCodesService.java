package com.example.jobisapplication.domain.code.usecase;

import com.example.jobisapplication.common.annotation.ReadOnlyUseCase;
import com.example.jobisapplication.domain.code.dto.response.CodesResponse;
import com.example.jobisapplication.domain.code.dto.response.CodesResponse.CodeResponse;
import com.example.jobisapplication.domain.code.model.CodeType;
import com.example.jobisapplication.domain.code.spi.QueryCodePort;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
@ReadOnlyUseCase
public class QueryCodesService {

    private final QueryCodePort queryCodePort;

    public CodesResponse execute(String keyword, CodeType codeType, Long parentCode) {
        List<CodeResponse> codes = queryCodePort.queryCodesByKeywordAndType(
                        keyword, codeType, parentCode
                ).stream()
                .map(CodesResponse::of)
                .toList();

        return new CodesResponse(codes);
    }
}
