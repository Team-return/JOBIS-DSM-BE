package team.retum.jobis.domain.interview.usecase;

import lombok.RequiredArgsConstructor;
import team.retum.jobis.common.annotation.UseCase;
import team.retum.jobis.domain.interview.exception.DocumentNumberNotFoundException;
import team.retum.jobis.domain.interview.model.DocumentNumber;
import team.retum.jobis.domain.interview.spi.CommandDocumentNumberPort;
import team.retum.jobis.domain.interview.spi.QueryDocumentNumberPort;

@RequiredArgsConstructor
@UseCase
public class DeleteDocumentNumberUseCase {

    private final CommandDocumentNumberPort commandDocumentNumberPort;
    private final QueryDocumentNumberPort queryDocumentNumberPort;

    public void execute(Long documentNumberId) {
        DocumentNumber documentNumber = queryDocumentNumberPort.getById(documentNumberId)
            .orElseThrow(() -> DocumentNumberNotFoundException.EXCEPTION);

        commandDocumentNumberPort.delete(documentNumber);
    }
}
