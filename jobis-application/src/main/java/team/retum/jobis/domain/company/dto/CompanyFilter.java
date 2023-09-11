package team.retum.jobis.domain.company.dto;

import lombok.Builder;
import lombok.Getter;
import team.retum.jobis.domain.company.model.CompanyType;

@Getter
@Builder
public class CompanyFilter {

    private final CompanyType type;
    private final String name;
    private final String region;
    private final String businessArea;
    private final Integer year;
    private final Long page;
    @Builder.Default
    private int limit = 11;

    public Long getOffset() {
        return page * limit;
    }
}
