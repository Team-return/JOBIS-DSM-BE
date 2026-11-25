package team.retum.jobis.domain.application.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import team.retum.jobis.domain.application.model.ApplicationStatus;
import team.retum.jobis.domain.application.spi.vo.ApplicationVO;

import java.time.LocalDate;
import java.util.List;

@Getter
@AllArgsConstructor
public class QueryMyApplicationsResponse {

    private final List<QueryMyApplicationResponse> applications;

    public static QueryMyApplicationsResponse from(List<ApplicationVO> applicationVOs) {
        List<Long> inProgressApplicationIds = applicationVOs.stream()
            .filter(application -> ApplicationStatus.DUPLICATE_CHECK.contains(application.getApplicationStatus()))
            .map(ApplicationVO::getId)
            .toList();

        return new QueryMyApplicationsResponse(
            applicationVOs.stream()
                .map(application -> QueryMyApplicationResponse.builder()
                    .applicationId(application.getId())
                    .recruitmentId(application.getRecruitmentId())
                    .company(application.getCompanyName())
                    .companyLogoUrl(application.getCompanyLogoUrl())
                    .attachments(
                        application.getApplicationAttachmentEntities().stream()
                            .map(AttachmentResponse::of)
                            .toList()
                    )
                    .applicationStatus(application.getApplicationStatus())
                    .createdAt(application.getCreatedAt().toLocalDate())
                    .isReapplicable(isReapplicable(application, inProgressApplicationIds))
                    .build()
                ).toList()
        );
    }

    private static boolean isReapplicable(ApplicationVO application, List<Long> inProgressApplicationIds) {
        boolean reapplicableStatus = application.getApplicationStatus() == ApplicationStatus.REJECTED
            || application.getApplicationStatus() == ApplicationStatus.REQUESTED;
        boolean hasOtherInProgress = inProgressApplicationIds.stream()
            .anyMatch(id -> !id.equals(application.getId()));

        return reapplicableStatus && !hasOtherInProgress;
    }

    @Getter
    @Builder
    public static class QueryMyApplicationResponse {

        private final Long applicationId;
        private final Long recruitmentId;
        private final String company;
        private final String companyLogoUrl;
        private final List<AttachmentResponse> attachments;
        private final ApplicationStatus applicationStatus;
        private final LocalDate createdAt;
        private final Boolean isReapplicable;
    }
}
