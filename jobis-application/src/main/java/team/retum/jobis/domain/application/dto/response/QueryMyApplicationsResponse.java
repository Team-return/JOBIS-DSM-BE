package team.retum.jobis.domain.application.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import team.retum.jobis.domain.application.model.ApplicationStatus;
import team.retum.jobis.domain.application.spi.vo.ApplicationVO;

import java.util.List;

@Getter
@AllArgsConstructor
public class QueryMyApplicationsResponse {

    private final List<QueryMyApplicationResponse> applications;

    public static QueryMyApplicationsResponse of(List<ApplicationVO> applicationVOs) {
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
                                .build()
                        ).toList()
        );
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
    }
}
