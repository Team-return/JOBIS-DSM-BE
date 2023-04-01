package team.returm.jobis.domain.application.presentation.dto.response;

import team.returm.jobis.domain.application.domain.enums.ApplicationStatus;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.UUID;

@Getter
@Builder
public class StudentApplicationsResponse {

    private final Long applicationId;
    private final String company;
    private final List<String> attachmentUrlList;
    private final ApplicationStatus applicationStatus;
}
