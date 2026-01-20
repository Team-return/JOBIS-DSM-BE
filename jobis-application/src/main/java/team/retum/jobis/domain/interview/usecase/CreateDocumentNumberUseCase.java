package team.retum.jobis.domain.interview.usecase;

import lombok.RequiredArgsConstructor;
import team.retum.jobis.common.annotation.UseCase;
import team.retum.jobis.domain.interview.model.DocumentNumber;
import team.retum.jobis.domain.interview.spi.CommandDocumentNumberPort;

@RequiredArgsConstructor
@UseCase
public class CreateDocumentNumberUseCase {

    private final CommandDocumentNumberPort commandDocumentNumberPort;

    public void execute(String documentNumber) {
        commandDocumentNumberPort.save(
            DocumentNumber.builder()
                .documentNumber(documentNumber)
                .build()
        );
    }
}
