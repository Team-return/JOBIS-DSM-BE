package team.retum.jobis.domain.recruitment.dto.request;

import lombok.Builder;
import team.retum.jobis.domain.recruitment.model.ProgressType;

import java.time.LocalDate;
import java.util.List;

@Builder
public record ApplyRecruitmentRequest(
    List<CreateRecruitAreaRequest> areas,
    String additionalQualifications,
    String workingHours,
    boolean flexibleWorking,
    List<String> requiredLicenses,
    List<ProgressType> hiringProgress,
    int trainPay,
    String pay,
    String benefits,
    Boolean militarySupport,
    boolean personalContact,
    String submitDocument,
    LocalDate startDate,
    LocalDate endDate,
    boolean winterIntern,
    Boolean hireConvertible,
    String etc
) {

}
