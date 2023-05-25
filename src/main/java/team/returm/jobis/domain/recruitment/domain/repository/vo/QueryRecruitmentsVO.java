package team.returm.jobis.domain.recruitment.domain.repository.vo;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import team.returm.jobis.domain.company.domain.enums.CompanyType;
import team.returm.jobis.domain.recruitment.domain.enums.RecruitStatus;
import team.returm.jobis.domain.recruitment.domain.type.RecruitDate;
import java.util.Set;

@Getter
public class QueryRecruitmentsVO {

    private final Long recruitmentId;
    private final RecruitStatus recruitStatus;
    private final RecruitDate recruitDate;
    private final String companyName;
    private final CompanyType companyType;
    private final Integer trainingPay;
    private final boolean militarySupport;
    private final String companyLogoUrl;
    private final String recruitAreaList;
    private final Integer totalHiring;
    private final Long requestedApplicationCount;
    private final Long approvedApplicationCount;
    private final Long isBookmarked;

    @QueryProjection
    public QueryRecruitmentsVO(Long recruitmentId, RecruitStatus recruitStatus, RecruitDate recruitDate,
                               String companyName, CompanyType companyType, Integer trainingPay, boolean militarySupport,
                               String companyLogoUrl, String recruitAreaList, Integer totalHiring,
                               Long requestedApplicationCount, Long approvedApplicationCount, Long isBookmarked) {
        this.recruitmentId = recruitmentId;
        this.recruitStatus = recruitStatus;
        this.recruitDate = recruitDate;
        this.companyName = companyName;
        this.companyType = companyType;
        this.trainingPay = trainingPay;
        this.militarySupport = militarySupport;
        this.companyLogoUrl = companyLogoUrl;
        this.recruitAreaList = recruitAreaList;
        this.totalHiring = totalHiring;
        this.requestedApplicationCount = requestedApplicationCount;
        this.approvedApplicationCount = approvedApplicationCount;
        this.isBookmarked = isBookmarked;
    }
}
