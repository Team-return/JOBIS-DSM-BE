package com.example.jobisapplication.domain.application.usecase;

import com.example.jobisapplication.common.annotation.ReadOnlyUseCase;
import com.example.jobisapplication.domain.application.spi.QueryApplicationPort;
import com.example.jobisapplication.domain.student.model.Student;
import lombok.RequiredArgsConstructor;
import com.example.jobisapplication.domain.application.model.ApplicationStatus;
import com.example.jobisapplication.domain.application.dto.response.AttachmentResponse;
import com.example.jobisapplication.domain.application.dto.response.TeacherQueryApplicationsResponse;
import com.example.jobisapplication.domain.application.dto.response.TeacherQueryApplicationsResponse.TeacherQueryApplicationResponse;

@RequiredArgsConstructor
@ReadOnlyUseCase
public class TeacherQueryApplicationsService {

    private final QueryApplicationPort applicationPersistenceAdapter;

    public TeacherQueryApplicationsResponse execute(ApplicationStatus applicationStatus, String studentName, Long recruitmentId) {
        int totalPageCount = (int) Math.ceil(
                applicationPersistenceAdapter.queryApplicationCountByCondition(applicationStatus, studentName).doubleValue() / 11
        );
        return new TeacherQueryApplicationsResponse(
                applicationPersistenceAdapter.queryApplicationByConditions(
                                recruitmentId, null, applicationStatus, studentName).stream()
                        .map(application -> TeacherQueryApplicationResponse.builder()
                                .applicationId(application.getId())
                                .studentName(application.getName())
                                .studentGcn(Student.processGcn(
                                        application.getGrade(),
                                        application.getClassNumber(),
                                        application.getNumber())
                                )
                                .companyName(application.getCompanyName())
                                .attachments(
                                        application.getApplicationAttachmentEntities().stream()
                                                .map(AttachmentResponse::of).toList()
                                )
                                .createdAt(application.getCreatedAt().toLocalDate())
                                .applicationStatus(application.getApplicationStatus())
                                .build()
                        ).toList(), totalPageCount
        );
    }
}
