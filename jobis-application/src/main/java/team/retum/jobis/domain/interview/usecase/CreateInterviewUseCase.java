package team.retum.jobis.domain.interview.usecase;

import lombok.RequiredArgsConstructor;
import team.retum.jobis.common.annotation.UseCase;
import team.retum.jobis.common.spi.SecurityPort;
import team.retum.jobis.domain.auth.model.Authority;
import team.retum.jobis.domain.interview.dto.request.InterviewRequest;
import team.retum.jobis.domain.interview.exception.InvalidInterviewDateException;
import team.retum.jobis.domain.interview.exception.InvalidInterviewTypeException;
import team.retum.jobis.domain.interview.exception.InvalidStudentIdException;
import team.retum.jobis.domain.interview.model.Interview;
import team.retum.jobis.domain.interview.spi.CommandInterviewPort;
import team.retum.jobis.domain.recruitment.model.ProgressType;
import team.retum.jobis.domain.student.exception.StudentNotFoundException;
import team.retum.jobis.domain.student.model.Student;
import team.retum.jobis.domain.student.spi.QueryStudentPort;

@RequiredArgsConstructor
@UseCase
public class CreateInterviewUseCase {

    private final CommandInterviewPort commandInterviewPort;
    private final QueryStudentPort queryStudentPort;
    private final SecurityPort securityPort;

    public void execute(InterviewRequest request) {
        Long studentId = resolveStudentId(request);

        Student student = queryStudentPort.getById(studentId)
            .orElseThrow(() -> StudentNotFoundException.EXCEPTION);

        validateInterviewType(request);
        validateInterviewDates(request);

        commandInterviewPort.save(
            Interview.builder()
                .interviewType(request.interviewType())
                .startDate(request.startDate())
                .endDate(request.endDate())
                .interviewTime(request.interviewTime())
                .companyName(request.companyName())
                .location(request.location())
                .studentId(student.getId())
                .documentNumberId(request.documentNumberId())
                .build()
        );
    }

    private Long resolveStudentId(InterviewRequest request) {
        Authority authority = securityPort.getCurrentUserAuthority();

        if (authority == Authority.STUDENT) {
            return securityPort.getCurrentUserId();
        }

        if (request.studentId() == null) {
            throw InvalidStudentIdException.EXCEPTION;
        }

        return request.studentId();
    }

    private void validateInterviewType(InterviewRequest request) {
        if (request.interviewType() == ProgressType.DOCUMENT) {
            throw InvalidInterviewTypeException.EXCEPTION;
        }
    }

    private void validateInterviewDates(InterviewRequest request) {
        if (request.endDate() != null && request.startDate().isAfter(request.endDate())) {
            throw InvalidInterviewDateException.EXCEPTION;
        }
    }
}
