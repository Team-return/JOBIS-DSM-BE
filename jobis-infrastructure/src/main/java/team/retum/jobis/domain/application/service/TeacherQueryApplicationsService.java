package team.retum.jobis.domain.application.service;

import lombok.RequiredArgsConstructor;
import com.example.jobisapplication.domain.application.model.ApplicationStatus;
import team.retum.jobis.domain.application.persistence.repository.ApplicationRepository;
import com.example.jobisapplication.domain.application.dto.response.AttachmentResponse;
import com.example.jobisapplication.domain.application.dto.response.TeacherQueryApplicationsResponse;
import com.example.jobisapplication.domain.application.dto.response.TeacherQueryApplicationsResponse.TeacherQueryApplicationResponse;
import team.retum.jobis.domain.student.persistence.entity.StudentEntity;
import com.example.jobisapplication.common.annotation.ReadOnlyService;

@RequiredArgsConstructor
@ReadOnlyService
public class TeacherQueryApplicationsService {

    private final ApplicationRepository applicationRepository;

    public TeacherQueryApplicationsResponse execute(ApplicationStatus applicationStatus, String studentName, Long recruitmentId) {
        int totalPageCount = (int) Math.ceil(
                applicationRepository.getApplicationCountByCondition(applicationStatus, studentName).doubleValue() / 11
        );
        return new TeacherQueryApplicationsResponse(
                applicationRepository.queryApplicationByConditions(
                                recruitmentId, null, applicationStatus, studentName).stream()
                        .map(application -> TeacherQueryApplicationResponse.builder()
                                .applicationId(application.getId())
                                .studentName(application.getName())
                                .studentGcn(StudentEntity.processGcn(
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
