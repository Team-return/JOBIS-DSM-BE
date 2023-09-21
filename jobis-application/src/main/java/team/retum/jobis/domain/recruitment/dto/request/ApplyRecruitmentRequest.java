package team.retum.jobis.domain.recruitment.dto.request;

import lombok.Builder;
import lombok.Getter;
import team.retum.jobis.domain.recruitment.model.ProgressType;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Getter
@Builder
public class ApplyRecruitmentRequest {

    private List<CreateRecruitAreaRequest> areas;

    private Integer requiredGrade;

    private LocalTime startTime;

    private LocalTime endTime;

    private List<String> requiredLicenses;

    private List<ProgressType> hiringProgress;

    private int trainPay;

    private Integer pay;

    private String benefits;

    private boolean militarySupport;

    private boolean personalContact;

    private String submitDocument;

    private LocalDate startDate;

    private LocalDate endDate;

    private String etc;
}
