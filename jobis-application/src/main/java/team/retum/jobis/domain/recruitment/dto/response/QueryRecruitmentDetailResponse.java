package team.retum.jobis.domain.recruitment.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import team.retum.jobis.domain.recruitment.model.ProgressType;
import team.retum.jobis.domain.recruitment.spi.vo.RecruitmentDetailVO;

import java.time.LocalDate;
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
    private final String additionalQualifications;
    private final String workingHours;
    private final boolean flexibleWorking;
    private final List<String> requiredLicenses;
    private final List<ProgressType> hiringProgress;
    private final Integer trainPay;
    private final String pay;
    private final String benefits;
    private final Boolean militarySupport;
    private final String submitDocument;
    private final LocalDate startDate;
    private final LocalDate endDate;
    private final String etc;
    private final Boolean isApplicable;
    private final boolean isBookmarked;
    private final Boolean hireConvertible;

    public static QueryRecruitmentDetailResponse of(
        RecruitmentDetailVO recruitmentDetail, List<RecruitAreaResponse> recruitAreas,
        Boolean isApplicable, boolean authority
    ) {
        return QueryRecruitmentDetailResponse.builder()
            .recruitmentId(recruitmentDetail.getRecruitmentId())
            .companyId(recruitmentDetail.getCompanyId())
            .companyProfileUrl(recruitmentDetail.getCompanyProfileUrl())
            .companyName(recruitmentDetail.getCompanyName())
            .areas(recruitAreas)
            .additionalQualifications(recruitmentDetail.getAdditionalQualifications())
            .workingHours(recruitmentDetail.getWorkingHours())
            .flexibleWorking(recruitmentDetail.isFlexibleWorking())
            .requiredLicenses(recruitmentDetail.getRequiredLicenses())
            .hiringProgress(recruitmentDetail.getHiringProgress())
            .trainPay(authority ? 0 : recruitmentDetail.getTrainPay())
            .pay(authority ? "0" : recruitmentDetail.getPay())
            .benefits(recruitmentDetail.getBenefits())
            .militarySupport(recruitmentDetail.getMilitarySupport())
            .submitDocument(recruitmentDetail.getSubmitDocument())
            .startDate(recruitmentDetail.getStartDate())
            .endDate(recruitmentDetail.getEndDate())
            .etc(recruitmentDetail.getEtc())
            .isApplicable(isApplicable)
            .isBookmarked(recruitmentDetail.isBookmarked())
            .hireConvertible(recruitmentDetail.getHireConvertible())
            .build();
    }
}
