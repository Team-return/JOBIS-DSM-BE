package team.returm.jobis.domain.recruitment.presentation.dto.response;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class QueryMyRecruitmentResponse {
    private final UUID recruitmentId;
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
    private final List<String> hiringProgress;
    private final LocalDate startDate;
    private final LocalDate endDate;
    private final String etc;
}
