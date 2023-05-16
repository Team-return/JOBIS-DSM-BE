package team.returm.jobis.domain.recruitment.domain.repository.vo;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import team.returm.jobis.domain.company.domain.Company;
import team.returm.jobis.domain.recruitment.domain.Recruitment;

import java.util.Set;

@Getter
public class QueryRecruitmentsVO {
    private final Recruitment recruitment;
    private final Company company;
    private final Set<String> recruitAreaList;
    private final Integer totalHiring;
    private final Long requestedApplicationCount;
    private final Long approvedApplicationCount;
    private final Long isBookmarked;

    @QueryProjection
    public QueryRecruitmentsVO(Recruitment recruitment, Company company,
                               Set<String> recruitAreaList, Integer totalHiring, Long requestedApplicationCount, Long approvedApplicationCount, Long isBookmarked) {
        this.recruitment = recruitment;
        this.company = company;
        this.recruitAreaList = recruitAreaList;
        this.totalHiring = totalHiring;
        this.requestedApplicationCount = requestedApplicationCount;
        this.approvedApplicationCount = approvedApplicationCount;
        this.isBookmarked = isBookmarked;
    }
}
