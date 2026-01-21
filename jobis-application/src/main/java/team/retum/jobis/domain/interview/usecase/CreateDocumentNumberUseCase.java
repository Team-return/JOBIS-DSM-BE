package team.retum.jobis.domain.interview.usecase;

import lombok.RequiredArgsConstructor;
import team.retum.jobis.common.annotation.UseCase;
import team.retum.jobis.domain.interview.exception.DocumentNumberAlreadyExistsException;
import team.retum.jobis.domain.interview.exception.InterviewNotFoundException;
import team.retum.jobis.domain.interview.model.DocumentNumber;
import team.retum.jobis.domain.interview.model.Interview;
import team.retum.jobis.domain.interview.spi.CommandDocumentNumberPort;
import team.retum.jobis.domain.interview.spi.CommandInterviewPort;
import team.retum.jobis.domain.interview.spi.QueryDocumentNumberPort;
import team.retum.jobis.domain.interview.spi.QueryInterviewPort;

import java.util.List;

@RequiredArgsConstructor
@UseCase
public class CreateDocumentNumberUseCase {

    private final CommandDocumentNumberPort commandDocumentNumberPort;
    private final QueryDocumentNumberPort queryDocumentNumberPort;
    private final QueryInterviewPort queryInterviewPort;
    private final CommandInterviewPort commandInterviewPort;

    public void execute(String documentNumber, List<Long> interviewIds) {
        if (queryDocumentNumberPort.existsByDocumentNumber(documentNumber)) {
            throw DocumentNumberAlreadyExistsException.EXCEPTION;
        }

        DocumentNumber saved = commandDocumentNumberPort.save(
            DocumentNumber.builder()
                .documentNumber(documentNumber)
                .build()
        );

        if (interviewIds != null && !interviewIds.isEmpty()) {
            List<Interview> interviews = queryInterviewPort.getByIds(interviewIds);
            if (interviews.size() != interviewIds.size()) {
                throw InterviewNotFoundException.EXCEPTION;
            }
            interviews.forEach(interview -> {
                Interview updated = interview.toBuilder()
                    .documentNumberId(saved.getId())
                    .build();
                commandInterviewPort.save(updated);
            });
        }
    }
}
