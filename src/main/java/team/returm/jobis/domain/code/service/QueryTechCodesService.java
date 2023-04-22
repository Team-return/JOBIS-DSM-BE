package team.returm.jobis.domain.code.service;

import lombok.RequiredArgsConstructor;
import team.returm.jobis.domain.code.domain.enums.CodeType;
import team.returm.jobis.domain.code.domain.repository.CodeRepository;
import team.returm.jobis.domain.code.presentation.dto.response.CodesResponse;
import team.returm.jobis.domain.code.presentation.dto.response.CodesResponse.CodeResponse;
import team.returm.jobis.global.annotation.ReadOnlyService;
import team.returm.jobis.global.util.StringUtil;

@RequiredArgsConstructor
@ReadOnlyService
public class QueryTechCodesService {

    private final CodeRepository codeRepository;

    public CodesResponse execute(String keyword) {
        keyword = StringUtil.nullToEmpty(keyword);

        return new CodesResponse(
                codeRepository.queryCodeByKeywordContaining(keyword, CodeType.TECH)
                .stream()
                .map(code ->
                        CodeResponse.builder()
                                .code(code.getId())
                                .keyword(code.getKeyword())
                                .build()
                ).toList()
        );
    }
}
