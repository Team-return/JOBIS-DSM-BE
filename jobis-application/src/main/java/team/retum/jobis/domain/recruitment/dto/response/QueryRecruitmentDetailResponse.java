package team.retum.jobis.domain.recruitment.dto.response;

import lombok.Builder;
import lombok.Getter;
import team.retum.jobis.domain.recruitment.model.ProgressType;
import team.retum.jobis.domain.recruitment.spi.vo.RecruitmentDetailVO;

import java.time.LocalDate;
import java.util.List;

@Getter
@Builder
public class QueryRecruitmentDetailResponse {

    private final Long recruitmentId;
    private final Long companyId;
    private final String companyProfileUrl;
    private final String companyName;
    private final List<RecruitAreaResponse> areas;
    private final String preferentialTreatment;
    private final Integer requiredGrade;
    private final Integer workHours;
    private final List<String> requiredLicenses;

    private final List<ProgressType> hiringProgress;
    private final Integer trainPay;
    private final Integer pay;
    private final String benefits;
    private final Boolean military;
    private final String submitDocument;
    private final LocalDate startDate;
    private final LocalDate endDate;
    private final String etc;

    public static QueryRecruitmentDetailResponse of(RecruitmentDetailVO recruitmentDetail, List<RecruitAreaResponse> recruitAreas) {
        return QueryRecruitmentDetailResponse.builder()
                .recruitmentId(recruitmentDetail.getRecruitmentId())
                .companyId(recruitmentDetail.getCompanyId())
                .companyProfileUrl(recruitmentDetail.getCompanyProfileUrl())
                .companyName(recruitmentDetail.getCompanyName())
                .areas(recruitAreas)
                .preferentialTreatment(recruitmentDetail.getPreferentialTreatment())
                .requiredGrade(recruitmentDetail.getRequiredGrade())
                .workHours(recruitmentDetail.getWorkHours())
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
                .build();
    }

}
