package team.returm.jobis.domain.recruitment.presentation.dto.response;

import java.time.LocalDate;
import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class StudentRecruitDetailsResponse {

    private Long companyId;
    private List<RecruitAreaResponse> areas;
    private String preferentialTreatment;
    private Integer requiredGrade;
    private Integer workHours;
    private List<String> requiredLicenses;

    private List<String> hiringProgress;
    private Integer trainPay;
    private Integer pay;
    private String benefits;
    private Boolean military;
    private String submitDocument;
    private LocalDate startDate;
    private LocalDate endDate;
    private String etc;
}
