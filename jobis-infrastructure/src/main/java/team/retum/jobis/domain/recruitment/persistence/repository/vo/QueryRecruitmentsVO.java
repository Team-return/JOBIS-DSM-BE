package team.retum.jobis.domain.recruitment.persistence.repository.vo;

import com.example.jobisapplication.domain.company.model.CompanyType;
import com.example.jobisapplication.domain.recruitment.model.RecruitStatus;
import com.example.jobisapplication.domain.recruitment.spi.vo.RecruitmentVO;
import com.querydsl.core.annotations.QueryProjection;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class QueryRecruitmentsVO extends RecruitmentVO {

    @QueryProjection
    public QueryRecruitmentsVO(Long recruitmentId, RecruitStatus recruitStatus, LocalDate startDate,
                               LocalDate endDate, String companyName, CompanyType companyType,
                               Integer trainPay, boolean militarySupport, String companyLogoUrl,
                               String recruitAreaList, Integer totalHiring, Long requestedApplicationCount,
                               Long approvedApplicationCount, Long isBookmarked) {
        super(recruitmentId, recruitStatus, startDate, endDate, companyName,
                companyType, trainPay, militarySupport, companyLogoUrl, recruitAreaList,
                totalHiring, requestedApplicationCount, approvedApplicationCount, isBookmarked);
    }
}
