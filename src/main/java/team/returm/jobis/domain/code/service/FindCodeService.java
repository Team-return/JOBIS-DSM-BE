package team.returm.jobis.domain.code.service;

import team.returm.jobis.domain.code.domain.enums.CodeType;
import team.returm.jobis.domain.code.domain.repository.CodeRepository;
import team.returm.jobis.domain.code.presentation.dto.response.CodeResponse;
import team.returm.jobis.global.annotation.ReadOnlyService;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
@ReadOnlyService
public class FindCodeService {
    private final CodeRepository codeRepository;

    public List<CodeResponse> execute(String keyword, CodeType type) {
        return codeRepository.findByKeywordContainingAndCodeType(keyword, type).stream()
                .map(code ->
                        CodeResponse.builder()
                                .code(code.getId())
                            .keyword(code.getKeyword())
                            .build()
                )
                .toList();
    }
}
