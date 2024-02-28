package team.retum.jobis.domain.application.spi.vo;

import lombok.Builder;
import lombok.Getter;
import team.retum.jobis.domain.application.model.ApplicationAttachment;
import team.retum.jobis.domain.application.model.ApplicationStatus;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
public class ApplicationVO {

    private final Long id;
    private final Long recruitmentId;
    private final String name;
    private final Integer grade;
    private final Integer number;
    private final Integer classNumber;
    private final String profileImageUrl;
    private final String companyName;
    private final String companyLogoUrl;
    private final List<ApplicationAttachment> applicationAttachmentEntities;
    private final LocalDateTime createdAt;
    private final ApplicationStatus applicationStatus;
}
