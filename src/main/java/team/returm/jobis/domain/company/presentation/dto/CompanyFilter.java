package team.returm.jobis.domain.company.presentation.dto;

import lombok.Builder;
import lombok.Getter;
import team.returm.jobis.domain.company.domain.enums.CompanyType;

@Getter
@Builder
public class CompanyFilter {

    private CompanyType type;
    private String name;
    private String region;
    private String businessArea;
    private Long page;

    public Long getOffset() {
        return page * 11;
    }
}
