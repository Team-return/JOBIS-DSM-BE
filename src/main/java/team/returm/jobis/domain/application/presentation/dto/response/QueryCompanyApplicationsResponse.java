package team.returm.jobis.domain.application.presentation.dto.response;

import java.time.LocalDate;
import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class QueryCompanyApplicationsResponse {

    private final Long applicationId;
    private final String studentNumber;
    private final String studentName;
    private final List<String> applicationAttachmentUrl;
    private final LocalDate createdAt;
}
