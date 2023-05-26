package team.returm.jobis.domain.application.service;

import lombok.RequiredArgsConstructor;
import team.returm.jobis.domain.application.domain.enums.ApplicationStatus;
import team.returm.jobis.domain.application.domain.repository.ApplicationRepository;
import team.returm.jobis.domain.application.presentation.dto.response.TeacherQueryApplicationsResponse;
import team.returm.jobis.domain.application.presentation.dto.response.TeacherQueryApplicationsResponse.TeacherQueryApplicationResponse;
import team.returm.jobis.domain.student.domain.Student;
import team.returm.jobis.global.annotation.ReadOnlyService;

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
                                .studentGcn(Student.processGcn(
                                        application.getGrade(),
                                        application.getClassNumber(),
                                        application.getNumber())
                                )
                                .companyName(application.getCompanyName())
                                .applicationAttachmentUrl(application.getApplicationAttachmentUrl())
                                .createdAt(application.getCreatedAt().toLocalDate())
                                .applicationStatus(application.getApplicationStatus())
                                .build()
                        ).toList(), totalPageCount
        );
    }
}
