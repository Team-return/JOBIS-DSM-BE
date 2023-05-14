package team.returm.jobis.domain.code.service;

import lombok.RequiredArgsConstructor;
import team.returm.jobis.domain.code.domain.enums.CodeType;
import team.returm.jobis.domain.code.domain.repository.CodeRepository;
import team.returm.jobis.domain.code.presentation.dto.response.CodesResponse;
import team.returm.jobis.domain.code.presentation.dto.response.CodesResponse.CodeResponse;
import team.returm.jobis.global.annotation.ReadOnlyService;

import java.util.List;

@RequiredArgsConstructor
@ReadOnlyService
public class QueryCodesService {

    private final CodeRepository codeRepository;

    public CodesResponse execute(String keyword, CodeType codeType) {
        List<CodeResponse> codes = codeRepository.queryCodesByKeywordAndType(keyword, codeType).stream()
                .map(
                        code -> CodeResponse.builder()
                                .code(code.getId())
                                .keyword(code.getKeyword())
                                .jobType(code.getJobType())
                                .build()
                ).toList();

        return new CodesResponse(codes);
    }
}
