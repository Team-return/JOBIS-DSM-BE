package team.retum.jobis.domain.application.presentation.dto.response;

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
        private final List<String> applicationAttachmentUrl;
        private final LocalDate createdAt;
    }
}
