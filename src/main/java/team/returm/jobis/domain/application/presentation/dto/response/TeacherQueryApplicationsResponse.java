package team.returm.jobis.domain.application.presentation.dto.response;

import team.returm.jobis.domain.application.domain.enums.ApplicationStatus;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Getter
@Builder
public class TeacherQueryApplicationsResponse {

    private final UUID applicationId;
    private final String studentName;
    private final String studentGcn;
    private final String companyName;
    private final List<String> applicationAttachmentUrl;
    private final LocalDate createdAt;
    private final ApplicationStatus applicationStatus;
}
