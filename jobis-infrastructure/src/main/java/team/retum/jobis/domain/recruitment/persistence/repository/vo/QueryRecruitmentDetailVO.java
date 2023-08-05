package team.retum.jobis.domain.recruitment.persistence.repository.vo;

import com.querydsl.core.annotations.QueryProjection;
import team.retum.jobis.domain.recruitment.model.ProgressType;
import team.retum.jobis.domain.recruitment.spi.vo.RecruitmentDetailVO;

import java.time.LocalDate;
import java.util.List;

public class QueryRecruitmentDetailVO extends RecruitmentDetailVO {

    @QueryProjection
    public QueryRecruitmentDetailVO(Long companyId, String companyProfileUrl, String companyName,
                                    String preferentialTreatment, Integer requiredGrade, Integer workHours,
                                    List<String> requiredLicenses, List<ProgressType> hiringProgress,
                                    Integer trainPay, Integer pay, String benefits, Boolean military,
                                    String submitDocument, LocalDate startDate, LocalDate endDate, String etc) {
        super(companyId, companyProfileUrl, companyName, preferentialTreatment, requiredGrade, workHours,
                requiredLicenses, hiringProgress, trainPay, pay, benefits, military, submitDocument,
                startDate, endDate, etc);
    }
}
