package team.retum.jobis.domain.recruitment.dto.request;

import lombok.Builder;
import lombok.Getter;
import team.retum.jobis.domain.recruitment.model.ProgressType;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Getter
@Builder
public class UpdateRecruitmentRequest {

    private Integer requiredGrade;

    private LocalTime startTime;

    private LocalTime endTime;

    private List<String> requiredLicenses;

    private List<ProgressType> hiringProgress;

    private Integer trainPay;

    private String pay;

    private String benefits;

    private boolean military;

    private String submitDocument;

    private LocalDate startDate;

    private LocalDate endDate;

    private String etc;
}
