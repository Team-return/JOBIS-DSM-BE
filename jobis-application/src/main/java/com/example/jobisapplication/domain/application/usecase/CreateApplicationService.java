package com.example.jobisapplication.domain.application.usecase;

import com.example.jobisapplication.common.annotation.UseCase;
import com.example.jobisapplication.common.spi.SecurityPort;
import com.example.jobisapplication.domain.application.dto.request.CreateApplicationRequest;
import com.example.jobisapplication.domain.application.model.Application;
import com.example.jobisapplication.domain.application.model.ApplicationAttachment;
import com.example.jobisapplication.domain.application.spi.CommandApplicationPort;
import com.example.jobisapplication.domain.application.spi.QueryApplicationPort;
import com.example.jobisapplication.domain.recruitment.model.Recruitment;
import com.example.jobisapplication.domain.recruitment.spi.QueryRecruitmentPort;
import com.example.jobisapplication.domain.student.exception.StudentNotFoundException;
import com.example.jobisapplication.domain.student.model.Student;
import com.example.jobisapplication.domain.student.spi.QueryStudentPort;
import lombok.RequiredArgsConstructor;
import com.example.jobisapplication.domain.application.model.ApplicationStatus;
import com.example.jobisapplication.domain.application.exception.ApplicationAlreadyExistsException;
import com.example.jobisapplication.domain.recruitment.exception.RecruitmentNotFoundException;

import java.util.List;

@RequiredArgsConstructor
@UseCase
public class CreateApplicationService {

    private final CommandApplicationPort commandApplicationPort;
    private final QueryApplicationPort queryApplicationPort;
    private final QueryRecruitmentPort queryRecruitmentPort;
    private final SecurityPort securityPort;
    private final QueryStudentPort queryStudentPort;

    public void execute(CreateApplicationRequest request, Long recruitmentId) {
        Long currentUserId = securityPort.getCurrentUserId();
        Student student = queryStudentPort.queryStudentById(currentUserId)
                        .orElseThrow(() -> StudentNotFoundException.EXCEPTION);
        student.checkIs3rdGrade();

        Recruitment recruitment = queryRecruitmentPort.queryRecruitmentById(recruitmentId)
                .orElseThrow(() -> RecruitmentNotFoundException.EXCEPTION);
        recruitment.checkIsApplicable();

        if (queryApplicationPort.existsApplicationByStudentIdAndApplicationStatusIn(
                student.getId(),
                List.of(
                        ApplicationStatus.APPROVED,
                        ApplicationStatus.FIELD_TRAIN,
                        ApplicationStatus.PASS
                )
        )) {
            throw ApplicationAlreadyExistsException.EXCEPTION;
        }

        Application application = commandApplicationPort.saveApplication(
                Application.builder()
                        .studentId(student.getId())
                        .recruitmentId(recruitment.getId())
                        .applicationStatus(ApplicationStatus.REQUESTED)
                        .build()
        );

        List<ApplicationAttachment> applicationAttachments = request.getAttachments()
                .stream()
                .map(attachment ->
                        ApplicationAttachment.builder()
                                .attachmentUrl(attachment.getUrl())
                                .type(attachment.getType())
                                .applicationId(application.getId())
                                .build()
                ).toList();

        commandApplicationPort.saveAllApplicationAttachment(applicationAttachments);
    }
}
