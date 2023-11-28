package team.retum.jobis.domain.recruitment.persistence.repository.vo;

import com.querydsl.core.annotations.QueryProjection;
import team.retum.jobis.domain.recruitment.spi.vo.StudentRecruitmentVO;

public class QueryStudentRecruitmentsVO extends StudentRecruitmentVO {

    @QueryProjection
    public QueryStudentRecruitmentsVO(long recruitmentId, String companyName, int trainPay,
                                      boolean militarySupport, String companyLogoUrl, String jobCodes,
                                      boolean isBookmarked) {
        super(recruitmentId, companyName, trainPay, militarySupport, companyLogoUrl, jobCodes, isBookmarked);
    }
}
