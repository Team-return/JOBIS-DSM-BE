package com.example.jobis.domain.application.service;

import com.example.jobis.domain.application.presentation.dto.response.StudentApplicationsResponse;
import com.example.jobis.domain.application.domain.repository.ApplicationRepository;
import com.example.jobis.domain.application.presentation.dto.request.QueryApplicationsRequest;
import com.example.jobis.domain.student.domain.Student;
import com.example.jobis.domain.user.facade.UserFacade;
import com.example.jobis.global.annotation.ReadOnlyService;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
@ReadOnlyService
public class QueryStudentApplicationsService {

    private final ApplicationRepository applicationRepository;
    private final UserFacade userFacade;

    public List<StudentApplicationsResponse> execute() {
        Student student = userFacade.getCurrentStudent();

        QueryApplicationsRequest request =
                QueryApplicationsRequest.builder()
                .studentId(student.getId())
                .build();

        return applicationRepository.queryApplicationByConditions(request).stream()
                .map(a -> StudentApplicationsResponse.builder()
                        .applicationId(a.getId())
                        .company(a.getCompanyName())
                        .attachmentUrlList(a.getApplicationAttachmentUrl())
                        .applicationStatus(a.getApplicationStatus())
                        .build())
                .toList();
    }
}
