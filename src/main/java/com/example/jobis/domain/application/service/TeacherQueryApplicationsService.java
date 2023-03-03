package com.example.jobis.domain.application.service;

import com.example.jobis.domain.application.controller.dto.response.TeacherQueryApplicationsResponse;
import com.example.jobis.domain.application.domain.enums.ApplicationStatus;
import com.example.jobis.domain.application.domain.repository.ApplicationRepository;
import com.example.jobis.domain.application.domain.repository.vo.QueryApplicationsByConditionsVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class TeacherQueryApplicationsService {

    private final ApplicationRepository applicationRepository;

    @Transactional(readOnly = true)
    public List<TeacherQueryApplicationsResponse> execute(UUID recruitmentId, ApplicationStatus applicationStatus, String studentName) {
        return applicationRepository.queryApplicationByConditions(QueryApplicationsByConditionsVO.builder()
                        .recruitmentId(recruitmentId)
                        .studentId(null)
                        .neApplicationStatus(null)
                        .eqApplicationStatus(applicationStatus)
                        .studentName(studentName)
                        .build()).stream()
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
