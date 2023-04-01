package team.returm.jobis.domain.application.presentation.dto.response;

import java.util.List;
import lombok.Builder;
import lombok.Getter;
import team.returm.jobis.domain.application.domain.enums.ApplicationStatus;

@Getter
@Builder
public class StudentApplicationsResponse {

    private final Long applicationId;
    private final String company;
    private final List<String> attachmentUrlList;
    private final ApplicationStatus applicationStatus;
}
