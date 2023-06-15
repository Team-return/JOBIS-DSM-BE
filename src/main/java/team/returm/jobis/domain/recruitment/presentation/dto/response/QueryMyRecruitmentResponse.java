package team.returm.jobis.domain.recruitment.presentation.dto.response;

import lombok.Builder;
import lombok.Getter;
import team.returm.jobis.domain.recruitment.domain.enums.ProgressType;

import java.time.LocalDate;
import java.util.List;

@Getter
@Builder
public class QueryMyRecruitmentResponse {
    private final Long recruitmentId;
    private final Integer recruitYear;
    private final List<RecruitAreaResponse> areas;
    private final String preferentialTreatment;
    private final Integer requiredGrade;
    private final List<String> requiredLicenses;
    private final Integer workingHours;
    private final Integer trainingPay;
    private final Integer pay;
    private final String benefits;
    private final boolean militarySupport;
    private final List<ProgressType> hiringProgress;
    private final LocalDate startDate;
    private final LocalDate endDate;
    private final String etc;
}
