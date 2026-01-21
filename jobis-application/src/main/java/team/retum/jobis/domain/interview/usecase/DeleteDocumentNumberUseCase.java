package team.retum.jobis.domain.interview.usecase;

import lombok.RequiredArgsConstructor;
import team.retum.jobis.common.annotation.UseCase;
import team.retum.jobis.domain.interview.exception.DocumentNumberNotFoundException;
import team.retum.jobis.domain.interview.model.DocumentNumber;
import team.retum.jobis.domain.interview.model.Interview;
import team.retum.jobis.domain.interview.spi.CommandDocumentNumberPort;
import team.retum.jobis.domain.interview.spi.CommandInterviewPort;
import team.retum.jobis.domain.interview.spi.QueryDocumentNumberPort;
import team.retum.jobis.domain.interview.spi.QueryInterviewPort;

import java.util.List;

@RequiredArgsConstructor
@UseCase
public class DeleteDocumentNumberUseCase {

    private final CommandDocumentNumberPort commandDocumentNumberPort;
    private final QueryDocumentNumberPort queryDocumentNumberPort;
    private final QueryInterviewPort queryInterviewPort;
    private final CommandInterviewPort commandInterviewPort;

    public void execute(Long documentNumberId) {
        DocumentNumber documentNumber = queryDocumentNumberPort.getById(documentNumberId)
            .orElseThrow(() -> DocumentNumberNotFoundException.EXCEPTION);

        List<Interview> relatedInterviews = queryInterviewPort.getByDocumentNumberId(documentNumberId);
        relatedInterviews.forEach(interview -> {
            Interview updated = interview.toBuilder()
                .documentNumberId(null)
                .build();
            commandInterviewPort.save(updated);
        });

        commandDocumentNumberPort.delete(documentNumber);
    }
}
