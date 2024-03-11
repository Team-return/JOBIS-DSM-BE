package team.retum.jobis.domain.code.usecase;

import lombok.RequiredArgsConstructor;
import team.retum.jobis.common.annotation.UseCase;
import team.retum.jobis.domain.code.dto.request.CreateCodeRequest;
import team.retum.jobis.domain.code.dto.response.CreateCodeResponse;
import team.retum.jobis.domain.code.model.Code;
import team.retum.jobis.domain.code.spi.CommandCodePort;
import team.retum.jobis.domain.code.spi.QueryCodePort;

@RequiredArgsConstructor
@UseCase
public class CreateCodeUseCase {

    private final CommandCodePort commandCodePort;
    private final QueryCodePort queryCodePort;

    public CreateCodeResponse execute(CreateCodeRequest request) {
        return queryCodePort.queryCodeByKeywordAndType(request.keyword(), request.codeType())
                .map(code -> new CreateCodeResponse(commandCodePort.saveCode(code.changeAccessible(true)).getId()))
                .orElseGet(() -> new CreateCodeResponse(createCode(request).getId()));
    }

    private Code createCode(CreateCodeRequest request) {
        return commandCodePort.saveCode(
                Code.builder()
                        .codeType(request.codeType())
                        .jobType(request.jobType())
                        .keyword(request.keyword())
                        .isPublic(false)
                        .build()
        );
    }
}
