package team.retum.jobis.domain.recruitment.dto.response;

import lombok.Builder;
import lombok.Getter;
import team.retum.jobis.domain.recruitment.model.ProgressType;

import java.time.LocalDate;
import java.time.LocalTime;
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
    private final LocalTime startTime;
    private final LocalTime endTime;
    private final Integer trainPay;
    private final Integer pay;
    private final String benefits;
    private final boolean militarySupport;
    private final List<ProgressType> hiringProgress;
    private final LocalDate startDate;
    private final LocalDate endDate;
    private final String etc;
}
