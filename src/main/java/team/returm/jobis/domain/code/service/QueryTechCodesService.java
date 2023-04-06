package team.returm.jobis.domain.code.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import team.returm.jobis.domain.code.domain.enums.CodeType;
import team.returm.jobis.domain.code.domain.repository.CodeJpaRepository;
import team.returm.jobis.domain.code.presentation.dto.response.CodeResponse;
import team.returm.jobis.global.annotation.ReadOnlyService;

@RequiredArgsConstructor
@ReadOnlyService
public class QueryTechCodesService {
    private final CodeJpaRepository codeJpaRepository;

    public List<CodeResponse> execute(String keyword) {
        if (keyword == null) {
            keyword = "";
        }

        return codeJpaRepository.queryCodeByKeywordContaining(
                        keyword
                ).stream()
                .map(code ->
                        CodeResponse.builder()
                                .code(code.getId())
                                .keyword(code.getKeyword())
                                .build()
                )
                .toList();
    }
}
