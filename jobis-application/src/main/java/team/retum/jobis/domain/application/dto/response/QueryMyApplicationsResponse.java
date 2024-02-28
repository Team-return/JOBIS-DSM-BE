package team.retum.jobis.domain.application.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import team.retum.jobis.domain.application.model.ApplicationStatus;

import java.util.List;

@Getter
@AllArgsConstructor
public class QueryMyApplicationsResponse {

    private final List<QueryMyApplicationResponse> applications;

    @Getter
    @Builder
    public static class QueryMyApplicationResponse {
        private final Long applicationId;
        private final Long recruitmentId;
        private final String company;
        private final String companyLogoUrl;
        private final List<AttachmentResponse> attachments;
        private final ApplicationStatus applicationStatus;
    }
}
