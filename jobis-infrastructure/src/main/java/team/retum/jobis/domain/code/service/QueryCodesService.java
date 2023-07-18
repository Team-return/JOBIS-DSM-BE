package team.retum.jobis.domain.code.service;

import lombok.RequiredArgsConstructor;
import team.retum.jobis.domain.code.persistence.enums.CodeType;
import team.retum.jobis.domain.code.persistence.repository.CodeRepository;
import team.retum.jobis.domain.code.presentation.dto.response.CodesResponse;
import team.retum.jobis.domain.code.presentation.dto.response.CodesResponse.CodeResponse;
import com.example.jobisapplication.common.annotation.ReadOnlyService;

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
