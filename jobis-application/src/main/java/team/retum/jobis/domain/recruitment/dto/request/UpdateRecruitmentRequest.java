package team.retum.jobis.domain.recruitment.dto.request;

import lombok.Builder;
import team.retum.jobis.domain.recruitment.model.ProgressType;

import java.time.LocalDate;
import java.util.List;

@Builder
public record UpdateRecruitmentRequest(
    String additionalQualifications,
    String workingHours,
    boolean flexibleWorking,
    List<String> requiredLicenses,
    List<ProgressType> hiringProgress,
    Integer trainPay,
    String pay,
    String benefits,
    Boolean militarySupport,
    String submitDocument,
    LocalDate startDate,
    LocalDate endDate,
    Boolean hireConvertible,
    String etc,
    Boolean integrationPlan
) {

}
