package team.retum.jobis.domain.recruitment.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import team.retum.jobis.domain.recruitment.model.ProgressType;
import team.retum.jobis.domain.recruitment.spi.vo.RecruitmentDetailVO;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Getter
@NoArgsConstructor(force = true)
@AllArgsConstructor
@Builder
public class QueryRecruitmentDetailResponse {

    private final Long recruitmentId;
    private final Long companyId;
    private final String companyProfileUrl;
    private final String companyName;
    private final List<RecruitAreaResponse> areas;
    private final Integer requiredGrade;
    private final LocalTime startTime;
    private final LocalTime endTime;
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
    private final Boolean isApplyPossible;
    private final boolean isBookmarked;

    public static QueryRecruitmentDetailResponse of(RecruitmentDetailVO recruitmentDetail, List<RecruitAreaResponse> recruitAreas, Boolean isApplyPossible) {
        return QueryRecruitmentDetailResponse.builder()
                .recruitmentId(recruitmentDetail.getRecruitmentId())
                .companyId(recruitmentDetail.getCompanyId())
                .companyProfileUrl(recruitmentDetail.getCompanyProfileUrl())
                .companyName(recruitmentDetail.getCompanyName())
                .areas(recruitAreas)
                .requiredGrade(recruitmentDetail.getRequiredGrade())
                .startTime(recruitmentDetail.getStartTime())
                .endTime(recruitmentDetail.getEndTime())
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
                .isApplyPossible(isApplyPossible)
                .isBookmarked(recruitmentDetail.getIsBookmarked() != 0)
                .build();
    }
}
