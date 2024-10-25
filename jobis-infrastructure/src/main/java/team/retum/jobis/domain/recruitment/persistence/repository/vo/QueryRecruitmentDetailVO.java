package team.retum.jobis.domain.recruitment.persistence.repository.vo;

import com.querydsl.core.annotations.QueryProjection;
import team.retum.jobis.domain.recruitment.model.ProgressType;
import team.retum.jobis.domain.recruitment.spi.vo.RecruitmentDetailVO;

import java.time.LocalDate;
import java.util.List;

public class QueryRecruitmentDetailVO extends RecruitmentDetailVO {

    @QueryProjection
    public QueryRecruitmentDetailVO(Long recruitmentId, Long companyId, String companyProfileUrl, String companyName,
                                    Integer requiredGrade, String workingHours, boolean flexibleWorking, List<String> requiredLicenses,
                                    List<ProgressType> hiringProgress, Integer trainPay, String pay, String benefits,
                                    Boolean military, String submitDocument, LocalDate startDate, LocalDate endDate,
                                    String etc, String companyBizNo, boolean winterIntern, boolean isBookmarked, Boolean hireConvertible) {
        super(recruitmentId, companyId, companyProfileUrl, companyName,
            requiredGrade, workingHours, flexibleWorking, requiredLicenses,
            hiringProgress, trainPay, pay, benefits, military,
            submitDocument, startDate, endDate, etc, companyBizNo, winterIntern, isBookmarked, hireConvertible);
    }
}
