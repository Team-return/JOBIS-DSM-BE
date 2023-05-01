package team.returm.jobis.domain.application.presentation.dto.response;

import java.time.LocalDate;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CompanyQueryApplicationsResponse {

    private final List<CompanyQueryApplicationResponse> applications;
    private final Integer count;

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
