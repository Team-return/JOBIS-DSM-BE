package team.returm.jobis.domain.application.presentation.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Getter
@Builder
public class QueryCompanyApplicationsResponse {

    private final Long applicationId;
    private final String studentNumber;
    private final String studentName;
    private final List<String> applicationAttachmentUrl;
    private final LocalDate createdAt;
}
