package com.example.jobisapplication.domain.application.usecase;

import com.example.jobisapplication.common.annotation.ReadOnlyUseCase;
import com.example.jobisapplication.domain.application.spi.QueryApplicationPort;
import com.example.jobisapplication.domain.student.model.Student;
import lombok.RequiredArgsConstructor;
import com.example.jobisapplication.domain.application.dto.response.AttachmentResponse;
import com.example.jobisapplication.domain.application.dto.response.StudentQueryApplicationsResponse;
import com.example.jobisapplication.domain.application.dto.response.StudentQueryApplicationsResponse.StudentQueryApplicationResponse;

@RequiredArgsConstructor
@ReadOnlyUseCase
public class QueryStudentApplicationsService {

    private final QueryApplicationPort queryApplicationPort;
    private final UserFacade userFacade;

    public StudentQueryApplicationsResponse execute() {
        Student student = userFacade.getCurrentStudent();

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
