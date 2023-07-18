package team.retum.jobis.domain.code.service;

import lombok.RequiredArgsConstructor;
import team.retum.jobis.domain.code.domain.enums.CodeType;
import team.retum.jobis.domain.code.domain.repository.CodeRepository;
import team.retum.jobis.domain.code.presentation.dto.response.CodesResponse;
import team.retum.jobis.domain.code.presentation.dto.response.CodesResponse.CodeResponse;
import team.retum.jobis.global.annotation.ReadOnlyService;

import java.util.List;

@RequiredArgsConstructor
@ReadOnlyService
public class QueryCodesService {

    private final CodeRepository codeRepository;

    public CodesResponse execute(String keyword, CodeType codeType, Long parentCode) {
        List<CodeResponse> codes = codeRepository.queryCodesByKeywordAndType(
                        keyword, codeType, parentCode
                ).stream()
                .map(CodesResponse::of)
                .toList();

        return new CodesResponse(codes);
    }
}
