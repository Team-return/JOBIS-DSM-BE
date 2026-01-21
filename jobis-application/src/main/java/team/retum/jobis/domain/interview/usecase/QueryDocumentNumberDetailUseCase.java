package team.retum.jobis.domain.interview.usecase;

import lombok.RequiredArgsConstructor;
import team.retum.jobis.common.annotation.ReadOnlyUseCase;
import team.retum.jobis.domain.interview.dto.response.QueryDocumentNumberDetailResponse;
import team.retum.jobis.domain.interview.exception.DocumentNumberNotFoundException;
import team.retum.jobis.domain.interview.spi.QueryDocumentNumberPort;
import team.retum.jobis.domain.interview.spi.vo.DocumentNumberDetailVO;

@RequiredArgsConstructor
@ReadOnlyUseCase
public class QueryDocumentNumberDetailUseCase {

    private final QueryDocumentNumberPort queryDocumentNumberPort;

    public QueryDocumentNumberDetailResponse execute(Long documentNumberId) {
        DocumentNumberDetailVO documentNumberDetail = queryDocumentNumberPort.getDetailById(documentNumberId)
            .orElseThrow(() -> DocumentNumberNotFoundException.EXCEPTION);

        return new QueryDocumentNumberDetailResponse(documentNumberDetail);
    }
}
