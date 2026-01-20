package team.retum.jobis.domain.interview.usecase;

import lombok.RequiredArgsConstructor;
import team.retum.jobis.common.annotation.ReadOnlyUseCase;
import team.retum.jobis.domain.interview.dto.response.QueryDocumentNumbersResponse;
import team.retum.jobis.domain.interview.model.DocumentNumber;
import team.retum.jobis.domain.interview.spi.QueryDocumentNumberPort;

import java.util.List;

@RequiredArgsConstructor
@ReadOnlyUseCase
public class QueryDocumentNumbersUseCase {

    private final QueryDocumentNumberPort queryDocumentNumberPort;

    public QueryDocumentNumbersResponse execute() {
        List<DocumentNumber> documentNumbers = queryDocumentNumberPort.getAll();
        return new QueryDocumentNumbersResponse(documentNumbers);
    }
}
