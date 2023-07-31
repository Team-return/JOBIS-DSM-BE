package team.retum.jobis.domain.company.model;

import team.retum.jobis.common.annotation.Aggregate;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@Aggregate
public class CompanyAttachment {

    private final Long id;

    private final String attachmentUrl;

    private final Long companyId;
}
