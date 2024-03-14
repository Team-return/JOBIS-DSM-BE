package team.retum.jobis.domain.recruitment.dto.response;

import lombok.Builder;
import lombok.Getter;
import team.retum.jobis.domain.recruitment.model.ProgressType;
import team.retum.jobis.domain.recruitment.spi.vo.RecruitmentDetailVO;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Getter
@Builder
public class QueryMyRecruitmentResponse {

    private final Long recruitmentId;
    private final Long companyId;
    private final String companyProfileUrl;
    private final String companyName;
    private final List<RecruitAreaResponse> areas;
    private final Integer requiredGrade;
    private final String workingHours;
    private final boolean flexibleWorking;
    private final List<String> requiredLicenses;
    private final List<ProgressType> hiringProgress;
    private final Integer trainPay;
    private final String pay;
    private final String benefits;
    private final Boolean military;
    private final String submitDocument;
    private final LocalDate startDate;
    private final LocalDate endDate;
    private final String etc;
    private final String companyBizNo;

    public static QueryMyRecruitmentResponse of(RecruitmentDetailVO recruitmentDetail, List<RecruitAreaResponse> recruitAreas) {
        return QueryMyRecruitmentResponse.builder()
                .recruitmentId(recruitmentDetail.getRecruitmentId())
                .companyId(recruitmentDetail.getCompanyId())
                .companyProfileUrl(recruitmentDetail.getCompanyProfileUrl())
                .companyName(recruitmentDetail.getCompanyName())
                .workingHours(recruitmentDetail.getWorkingHours())
                .flexibleWorking(recruitmentDetail.isFlexibleWorking())
                .areas(recruitAreas)
                .requiredGrade(recruitmentDetail.getRequiredGrade())
                .requiredLicenses(recruitmentDetail.getRequiredLicenses())
                .hiringProgress(recruitmentDetail.getHiringProgress())
                .trainPay(recruitmentDetail.getTrainPay())
                .pay(recruitmentDetail.getPay())
                .benefits(recruitmentDetail.getBenefits())
                .military(recruitmentDetail.getMilitary())
                .submitDocument(recruitmentDetail.getSubmitDocument())
                .startDate(recruitmentDetail.getStartDate())
                .endDate(recruitmentDetail.getEndDate())
                .etc(recruitmentDetail.getEtc())
                .companyBizNo(recruitmentDetail.getCompanyBizNo())
                .build();
    }
}
