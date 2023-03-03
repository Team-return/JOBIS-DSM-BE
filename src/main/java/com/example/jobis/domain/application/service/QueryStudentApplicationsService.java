package com.example.jobis.domain.application.service;

import com.example.jobis.domain.application.controller.dto.response.StudentApplicationListResponse;
import com.example.jobis.domain.application.domain.repository.ApplicationRepository;
import com.example.jobis.domain.student.domain.Student;
import com.example.jobis.domain.student.facade.StudentFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Year;
import java.util.List;

@RequiredArgsConstructor
@Service
public class QueryStudentApplicationListService {

    private final ApplicationRepository applicationRepository;
    private final StudentFacade studentFacade;

    @Transactional(readOnly = true)
    public List<StudentApplicationListResponse> execute() {
        Student student = studentFacade.getCurrentStudent();
        return applicationRepository.queryApplicationByConditions(null, student.getId(), null, null, Year.now().getValue(), null).stream()
                .map(a -> StudentApplicationListResponse.builder()
                        .applicationId(a.getApplicationId())
                        .student(a.getStudentName())
                        .company(a.getCompanyName())
                        .attachmentUrlList(a.getApplicationAttachmentUrl())
                        .applicationStatus(a.getApplicationStatus())
                        .build())
                .toList();
    }
}
