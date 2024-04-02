package team.retum.jobis.domain.recruitment.dto.request;

import lombok.Builder;
import team.retum.jobis.domain.recruitment.model.ProgressType;

import java.time.LocalDate;
import java.util.List;

@Builder
public record ApplyRecruitmentRequest(
    List<CreateRecruitAreaRequest> areas,
    Integer requiredGrade,
    String workingHours,
    boolean flexibleWorking,
    List<String> requiredLicenses,
    List<ProgressType> hiringProgress,
    int trainPay,
    String pay,
    String benefits,
    boolean militarySupport,
    boolean personalContact,
    String submitDocument,
    LocalDate startDate,
    LocalDate endDate,
    boolean winterIntern,
    String etc
) {

}
