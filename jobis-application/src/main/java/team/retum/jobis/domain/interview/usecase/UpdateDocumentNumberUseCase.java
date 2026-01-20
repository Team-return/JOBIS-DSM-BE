package team.retum.jobis.domain.interview.usecase;

import lombok.RequiredArgsConstructor;
import team.retum.jobis.common.annotation.UseCase;
import team.retum.jobis.domain.interview.exception.DocumentNumberNotFoundException;
import team.retum.jobis.domain.interview.model.DocumentNumber;
import team.retum.jobis.domain.interview.spi.CommandDocumentNumberPort;
import team.retum.jobis.domain.interview.spi.QueryDocumentNumberPort;

@RequiredArgsConstructor
@UseCase
public class UpdateDocumentNumberUseCase {

    private final CommandDocumentNumberPort commandDocumentNumberPort;
    private final QueryDocumentNumberPort queryDocumentNumberPort;

    public void execute(Long documentNumberId, String documentNumber) {
        DocumentNumber existingDocumentNumber = queryDocumentNumberPort.getById(documentNumberId)
            .orElseThrow(() -> DocumentNumberNotFoundException.EXCEPTION);

        commandDocumentNumberPort.save(
            DocumentNumber.builder()
                .id(existingDocumentNumber.getId())
                .documentNumber(documentNumber)
                .build()
        );
    }
}
