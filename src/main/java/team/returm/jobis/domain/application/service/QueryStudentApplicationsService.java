package team.returm.jobis.domain.application.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import team.returm.jobis.domain.application.domain.repository.ApplicationRepository;
import team.returm.jobis.domain.application.presentation.dto.request.QueryApplicationsRequest;
import team.returm.jobis.domain.application.presentation.dto.response.StudentApplicationsResponse;
import team.returm.jobis.domain.student.domain.Student;
import team.returm.jobis.domain.user.facade.UserFacade;
import team.returm.jobis.global.annotation.ReadOnlyService;

@RequiredArgsConstructor
@ReadOnlyService
public class QueryStudentApplicationsService {

    private final ApplicationRepository applicationRepository;
    private final UserFacade userFacade;

    public List<StudentApplicationsResponse> execute() {
        Student student = userFacade.getCurrentStudent();

        return applicationRepository.queryApplicationByConditions(
                null, student.getId(), null, null, null).stream()
                .map(a -> StudentApplicationsResponse.builder()
                        .applicationId(a.getId())
                        .company(a.getCompanyName())
                        .attachmentUrlList(a.getApplicationAttachmentUrl())
                        .applicationStatus(a.getApplicationStatus())
                        .build())
                .toList();
    }
}
