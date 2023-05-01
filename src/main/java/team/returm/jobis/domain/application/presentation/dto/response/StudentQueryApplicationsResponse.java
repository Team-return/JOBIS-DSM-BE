package team.returm.jobis.domain.application.presentation.dto.response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import team.returm.jobis.domain.application.domain.enums.ApplicationStatus;

@Getter
@AllArgsConstructor
public class StudentQueryApplicationsResponse {

    private final List<StudentQueryApplicationResponse> applications;
    private final Integer count;

    @Getter
    @Builder
    public static class StudentQueryApplicationResponse {

        private final Long applicationId;
        private final String company;
        private final List<String> attachmentUrlList;
        private final ApplicationStatus applicationStatus;
    }
}
