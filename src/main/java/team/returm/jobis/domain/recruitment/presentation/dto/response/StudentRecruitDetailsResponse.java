package team.returm.jobis.domain.recruitment.presentation.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Getter
@Builder
public class StudentRecruitDetailsResponse {

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

    @Getter
    @Builder
    public static class RecruitAreaResponse {
        private UUID recruitAreaId;
        private List<String> job;
        private List<String> tech;
        private int hiring;
        private String majorTask;
    }
}
