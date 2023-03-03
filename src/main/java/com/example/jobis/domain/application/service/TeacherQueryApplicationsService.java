package com.example.jobis.domain.application.service;

import com.example.jobis.domain.application.controller.dto.response.TeacherQueryApplicationsResponse;
import com.example.jobis.domain.application.domain.enums.ApplicationStatus;
import com.example.jobis.domain.application.domain.repository.ApplicationRepository;
import com.example.jobis.domain.application.controller.dto.request.QueryApplicationsRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class TeacherQueryApplicationsService {

    private final ApplicationRepository applicationRepository;

    @Transactional(readOnly = true)
    public List<TeacherQueryApplicationsResponse> execute(ApplicationStatus applicationStatus, String studentName) {
        QueryApplicationsRequest request =
                QueryApplicationsRequest.builder()
                .applicationStatus(applicationStatus)
                .studentName(studentName)
                .build();

        return applicationRepository.queryApplicationByConditions(request).stream()
                .map(a -> TeacherQueryApplicationsResponse.builder()
                        .applicationId(a.getApplicationId())
                        .studentName(a.getStudentName())
                        .studentNumber(a.getStudentNumber())
                        .companyName(a.getCompanyName())
                        .applicationAttachmentUrl(a.getApplicationAttachmentUrl())
                        .createdAt(a.getCreatedAt().toLocalDate())
                        .applicationStatus(a.getApplicationStatus())
                        .build())
                .toList();
    }
}
