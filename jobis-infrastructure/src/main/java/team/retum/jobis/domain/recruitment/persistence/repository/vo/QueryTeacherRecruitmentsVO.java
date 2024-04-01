package team.retum.jobis.domain.recruitment.persistence.repository.vo;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import team.retum.jobis.domain.company.model.CompanyType;
import team.retum.jobis.domain.recruitment.model.RecruitStatus;
import team.retum.jobis.domain.recruitment.spi.vo.TeacherRecruitmentVO;

import java.time.LocalDate;

@Getter
public class QueryTeacherRecruitmentsVO extends TeacherRecruitmentVO {

    @QueryProjection
    public QueryTeacherRecruitmentsVO(long recruitmentId, RecruitStatus recruitStatus,
                                      LocalDate startDate, LocalDate endDate, String companyName,
                                      CompanyType companyType, String jobCodes, long totalHiring,
                                      long requestedApplicationCount, long approvedApplicationCount,
                                      long companyId) {
        super(recruitmentId, recruitStatus, startDate, endDate,
            companyName, companyType, jobCodes, totalHiring,
            requestedApplicationCount, approvedApplicationCount,
            companyId);
    }
}
