package team.retum.jobis.domain.company.model;

import lombok.Builder;
import lombok.Getter;
import team.retum.jobis.common.annotation.Aggregate;

@Getter
@Builder
@Aggregate
public class CompanyAttachment {

    private final Long id;

    private final String attachmentUrl;

    private final Long companyId;
}
