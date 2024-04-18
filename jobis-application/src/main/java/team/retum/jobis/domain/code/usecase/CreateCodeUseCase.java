package team.retum.jobis.domain.code.usecase;

import lombok.RequiredArgsConstructor;
import team.retum.jobis.common.annotation.UseCase;
import team.retum.jobis.domain.code.dto.request.CreateCodeRequest;
import team.retum.jobis.domain.code.dto.response.CreateCodeResponse;
import team.retum.jobis.domain.code.model.Code;
import team.retum.jobis.domain.code.spi.CodePort;

@RequiredArgsConstructor
@UseCase
public class CreateCodeUseCase {

    private final CodePort codePort;

    public CreateCodeResponse execute(CreateCodeRequest request) {
        return codePort.getByKeywordAndType(request.keyword(), request.codeType())
            .map(code -> new CreateCodeResponse(codePort.save(code.changeAccessible(true)).getId()))
            .orElseGet(() -> new CreateCodeResponse(createCode(request).getId()));
    }

    private Code createCode(CreateCodeRequest request) {
        return codePort.save(
            Code.builder()
                .codeType(request.codeType())
                .jobType(request.jobType())
                .keyword(request.keyword())
                .isPublic(false)
                .build()
        );
    }
}
