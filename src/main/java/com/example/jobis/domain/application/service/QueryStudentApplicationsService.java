package com.example.jobis.domain.application.service;

import com.example.jobis.domain.application.controller.dto.response.StudentApplicationsResponse;
import com.example.jobis.domain.application.domain.repository.ApplicationRepository;
import com.example.jobis.domain.application.domain.repository.vo.QueryApplicationsByConditionsVO;
import com.example.jobis.domain.student.domain.Student;
import com.example.jobis.domain.student.facade.StudentFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Year;
import java.util.List;

@RequiredArgsConstructor
@Service
public class QueryStudentApplicationsService {

    private final ApplicationRepository applicationRepository;
    private final StudentFacade studentFacade;

    @Transactional(readOnly = true)
    public List<StudentApplicationsResponse> execute() {
        Student student = studentFacade.getCurrentStudent();
        return applicationRepository.queryApplicationByConditions(QueryApplicationsByConditionsVO.builder()
                        .recruitmentId(null)
                        .studentId(student.getId())
                        .neApplicationStatus(null)
                        .eqApplicationStatus(null)
                        .studentName(null)
                        .build()).stream()
                .map(a -> StudentApplicationsResponse.builder()
                        .applicationId(a.getApplicationId())
                        .company(a.getCompanyName())
                        .attachmentUrlList(a.getApplicationAttachmentUrl())
                        .applicationStatus(a.getApplicationStatus())
                        .build())
                .toList();
    }
}
