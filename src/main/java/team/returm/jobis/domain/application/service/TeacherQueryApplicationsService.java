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

    public TeacherQueryApplicationsResponse execute(ApplicationStatus applicationStatus, String studentName, Long companyId) {
        return new TeacherQueryApplicationsResponse(
                applicationRepository.queryApplicationByConditions(
                        null, null, applicationStatus, studentName, companyId).stream()
                .map(a -> TeacherQueryApplicationResponse.builder()
                        .applicationId(a.getId())
                        .studentName(a.getName())
                        .studentGcn(Student.processGcn(
                                a.getGrade(),
                                a.getClassNumber(),
                                a.getNumber())
                        )
                        .companyName(a.getCompanyName())
                        .applicationAttachmentUrl(a.getApplicationAttachmentUrl())
                        .createdAt(a.getCreatedAt().toLocalDate())
                        .applicationStatus(a.getApplicationStatus())
                        .build()
                ).toList()
        );
    }
}
