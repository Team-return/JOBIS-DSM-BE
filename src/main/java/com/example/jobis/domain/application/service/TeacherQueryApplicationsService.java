package com.example.jobis.domain.application.service;

import com.example.jobis.domain.application.controller.dto.response.TeacherQueryApplicationsResponse;
import com.example.jobis.domain.application.domain.enums.ApplicationStatus;
import com.example.jobis.domain.application.domain.repository.ApplicationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Year;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class QueryTeacherApplicationsService {

    private final ApplicationRepository applicationRepository;

    @Transactional(readOnly = true)
    public List<TeacherQueryApplicationsResponse> execute(UUID recruitmentId, ApplicationStatus applicationStatus, String studentName) {
        return applicationRepository.queryApplicationByConditions(recruitmentId, null, null, applicationStatus, Year.now().getValue(), studentName).stream()
                .map(a -> TeacherQueryApplicationsResponse.builder()
                        .applicationId(a.getApplicationId())
                        .studentName(a.getStudentName())
                        .studentNumber(a.getStudentNumber())
                        .applicationAttachmentUrl(a.getApplicationAttachmentUrl())
                        .createdAt(a.getCreatedAt().toLocalDate())
                        .applicationStatus(a.getApplicationStatus())
                        .build())
                .toList();
    }
}
