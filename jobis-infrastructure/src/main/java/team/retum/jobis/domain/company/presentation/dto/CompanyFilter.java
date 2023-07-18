package team.retum.jobis.domain.company.presentation.dto;

import lombok.Builder;
import lombok.Getter;
import com.example.jobisapplication.domain.company.model.CompanyType;

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
