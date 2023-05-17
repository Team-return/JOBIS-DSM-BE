package team.returm.jobis.domain.recruitment.presentation.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Builder
public class QueryRecruitmentDetailResponse {

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
