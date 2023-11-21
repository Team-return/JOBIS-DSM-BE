package team.retum.jobis.domain.recruitment.persistence.repository.vo;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import team.retum.jobis.domain.company.model.CompanyType;
import team.retum.jobis.domain.recruitment.model.RecruitStatus;
import team.retum.jobis.domain.recruitment.spi.vo.RecruitmentVO;

import java.time.LocalDate;

@Getter
public class QueryRecruitmentsVO extends RecruitmentVO {

    @QueryProjection
    public QueryRecruitmentsVO(Long recruitmentId, RecruitStatus recruitStatus, LocalDate startDate,
                               LocalDate endDate, String companyName, CompanyType companyType,
                               Integer trainPay, boolean militarySupport, String companyLogoUrl,
                               String jobCodes, Integer totalHiring, Long requestedApplicationCount,
                               Long approvedApplicationCount, Boolean isBookmarked, Long companyId) {
        super(recruitmentId, recruitStatus, startDate, endDate, companyName,
                companyType, trainPay, militarySupport, companyLogoUrl, jobCodes,
                totalHiring, requestedApplicationCount, approvedApplicationCount, isBookmarked, companyId);
    }
}
