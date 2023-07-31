package team.retum.jobis.domain.code.usecase;

import team.retum.jobis.common.annotation.ReadOnlyUseCase;
import team.retum.jobis.domain.code.dto.response.CodesResponse;
import team.retum.jobis.domain.code.dto.response.CodesResponse.CodeResponse;
import team.retum.jobis.domain.code.model.CodeType;
import team.retum.jobis.domain.code.spi.QueryCodePort;
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
