package com.example.jobisapplication.domain.application.usecase;

import com.example.jobisapplication.common.annotation.ReadOnlyUseCase;
import com.example.jobisapplication.common.spi.SecurityPort;
import com.example.jobisapplication.domain.application.spi.QueryApplicationPort;
import com.example.jobisapplication.domain.student.exception.StudentNotFoundException;
import com.example.jobisapplication.domain.student.model.Student;
import com.example.jobisapplication.domain.student.spi.QueryStudentPort;
import lombok.RequiredArgsConstructor;
import com.example.jobisapplication.domain.application.dto.response.AttachmentResponse;
import com.example.jobisapplication.domain.application.dto.response.StudentQueryApplicationsResponse;
import com.example.jobisapplication.domain.application.dto.response.StudentQueryApplicationsResponse.StudentQueryApplicationResponse;

@RequiredArgsConstructor
@ReadOnlyUseCase
public class QueryStudentApplicationsService {

    private final QueryApplicationPort queryApplicationPort;
    private final SecurityPort securityPort;
    private final QueryStudentPort queryStudentPort;

    public StudentQueryApplicationsResponse execute() {
        Long currentUserId = securityPort.getCurrentUserId();
        Student student = queryStudentPort.queryStudentById(currentUserId)
                .orElseThrow(() -> StudentNotFoundException.EXCEPTION);

        return new StudentQueryApplicationsResponse(
                queryApplicationPort.queryApplicationByConditions(
                                null, student.getId(), null, null).stream()
                        .map(application -> StudentQueryApplicationResponse.builder()
                                .applicationId(application.getId())
                                .company(application.getCompanyName())
                                .attachments(
                                        application.getApplicationAttachmentEntities().stream()
                                                .map(AttachmentResponse::of)
                                                .toList()
                                )
                                .applicationStatus(application.getApplicationStatus())
                                .build()
                        ).toList()
        );
    }
}
