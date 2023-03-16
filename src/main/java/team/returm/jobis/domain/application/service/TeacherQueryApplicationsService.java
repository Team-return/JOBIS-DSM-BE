package team.returm.jobis.domain.application.service;

import team.returm.jobis.domain.application.presentation.dto.response.TeacherQueryApplicationsResponse;
import team.returm.jobis.domain.application.domain.enums.ApplicationStatus;
import team.returm.jobis.domain.application.domain.repository.ApplicationRepository;
import team.returm.jobis.domain.application.presentation.dto.request.QueryApplicationsRequest;
import team.returm.jobis.domain.student.domain.Student;
import team.returm.jobis.global.annotation.ReadOnlyService;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
@ReadOnlyService
public class TeacherQueryApplicationsService {

    private final ApplicationRepository applicationRepository;

    public List<TeacherQueryApplicationsResponse> execute(ApplicationStatus applicationStatus, String studentName) {
        QueryApplicationsRequest request =
                QueryApplicationsRequest.builder()
                .applicationStatus(applicationStatus)
                .studentName(studentName)
                .build();

        return applicationRepository.queryApplicationByConditions(request).stream()
                .map(a -> TeacherQueryApplicationsResponse.builder()
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
                        .build())
                .toList();
    }
}
