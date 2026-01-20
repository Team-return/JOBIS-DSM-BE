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
public class UpdateDocumentNumberUseCase {

    private final CommandDocumentNumberPort commandDocumentNumberPort;
    private final QueryDocumentNumberPort queryDocumentNumberPort;
    private final QueryInterviewPort queryInterviewPort;
    private final CommandInterviewPort commandInterviewPort;

    public void execute(Long documentNumberId, String documentNumber, List<Long> interviewIds) {
        DocumentNumber existingDocumentNumber = queryDocumentNumberPort.getById(documentNumberId)
            .orElseThrow(() -> DocumentNumberNotFoundException.EXCEPTION);

        commandDocumentNumberPort.save(
            DocumentNumber.builder()
                .id(existingDocumentNumber.getId())
                .documentNumber(documentNumber)
                .build()
        );

        List<Interview> previousInterviews = queryInterviewPort.getByDocumentNumberId(documentNumberId);
        previousInterviews.forEach(interview -> {
            Interview updated = interview.toBuilder()
                .documentNumberId(null)
                .build();
            commandInterviewPort.save(updated);
        });

        if (interviewIds != null && !interviewIds.isEmpty()) {
            List<Interview> newInterviews = queryInterviewPort.getByIds(interviewIds);
            newInterviews.forEach(interview -> {
                Interview updated = interview.toBuilder()
                    .documentNumberId(documentNumberId)
                    .build();
                commandInterviewPort.save(updated);
            });
        }
    }
}
