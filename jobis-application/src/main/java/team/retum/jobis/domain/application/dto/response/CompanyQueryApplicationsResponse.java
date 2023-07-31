package team.retum.jobis.domain.application.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

@Getter
@AllArgsConstructor
public class CompanyQueryApplicationsResponse {

    private final List<CompanyQueryApplicationResponse> applications;

    @Getter
    @Builder
    public static class CompanyQueryApplicationResponse {

        private final Long applicationId;
        private final String studentNumber;
        private final String studentName;
        private final String profileImageUrl;
        private final List<AttachmentResponse> attachments;
        private final LocalDate createdAt;
    }
}
