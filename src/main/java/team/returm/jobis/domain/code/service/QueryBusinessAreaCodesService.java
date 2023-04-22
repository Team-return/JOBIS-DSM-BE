package team.returm.jobis.domain.code.service;

import lombok.RequiredArgsConstructor;
import team.returm.jobis.domain.code.domain.enums.CodeType;
import team.returm.jobis.domain.code.domain.repository.CodeRepository;
import team.returm.jobis.domain.code.presentation.dto.response.CodesResponse;
import team.returm.jobis.domain.code.presentation.dto.response.CodesResponse.CodeResponse;
import team.returm.jobis.global.annotation.Service;
import team.returm.jobis.global.util.StringUtil;

import java.util.List;

@RequiredArgsConstructor
@Service
public class QueryBusinessAreaCodesService {

    private final CodeRepository codeRepository;

    public CodesResponse execute(String keyword) {
        keyword = StringUtil.nullToEmpty(keyword);

        List<CodeResponse> codes = codeRepository.queryCodeByKeywordContaining(keyword, CodeType.BUSINESS_AREA).stream()
                .map(code -> CodeResponse.builder()
                        .code(code.getId())
                        .keyword(code.getKeyword())
                        .build()
                ).toList();

        return new CodesResponse(codes);
    }
}
