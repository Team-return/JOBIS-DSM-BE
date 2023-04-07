package team.returm.jobis.domain.code.service;

import lombok.RequiredArgsConstructor;
import team.returm.jobis.domain.code.domain.enums.CodeType;
import team.returm.jobis.domain.code.domain.repository.CodeRepository;
import team.returm.jobis.domain.code.presentation.dto.response.CodeResponse;
import team.returm.jobis.global.annotation.ReadOnlyService;

import java.util.List;

@RequiredArgsConstructor
@ReadOnlyService
public class QueryTechCodesService {
    private final CodeRepository codeRepository;

    public List<CodeResponse> execute(String keyword) {
        if (keyword == null) {
            keyword = "";
        }

        return codeRepository.queryCodeByKeywordContaining(keyword, CodeType.TECH).stream()
                .map(code ->
                        CodeResponse.builder()
                                .code(code.getId())
                                .keyword(code.getKeyword())
                                .build()
                )
                .toList();
    }
}
