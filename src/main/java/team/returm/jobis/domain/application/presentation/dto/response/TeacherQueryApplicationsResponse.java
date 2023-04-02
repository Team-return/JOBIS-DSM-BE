package team.returm.jobis.domain.application.presentation.dto.response;

import java.time.LocalDate;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import team.returm.jobis.domain.application.domain.enums.ApplicationStatus;

@Getter
@Builder
public class TeacherQueryApplicationsResponse {

    private final Long applicationId;
    private final String studentName;
    private final String studentGcn;
    private final String companyName;
    private final List<String> applicationAttachmentUrl;
    private final LocalDate createdAt;
    private final ApplicationStatus applicationStatus;
}
