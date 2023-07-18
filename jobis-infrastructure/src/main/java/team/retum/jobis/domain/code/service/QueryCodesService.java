package team.retum.jobis.domain.code.service;

import lombok.RequiredArgsConstructor;
import com.example.jobisapplication.domain.code.model.CodeType;
import team.retum.jobis.domain.code.persistence.repository.CodeRepository;
import com.example.jobisapplication.domain.code.dto.response.CodesResponse;
import com.example.jobisapplication.domain.code.dto.response.CodesResponse.CodeResponse;
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
