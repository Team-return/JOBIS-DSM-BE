package team.returm.jobis.domain.recruitment.presentation.dto.request;

import team.returm.jobis.domain.recruitment.domain.enums.ProgressType;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@Getter
@NoArgsConstructor
public class ApplyRecruitmentRequest {
    @NotNull @Valid
    private List<Area> areas;
    private String preferentialTreatment;
    private Integer requiredGrade;
    @NotNull
    private int workHours;
    private List<String> requiredLicenses;
    @NotNull
    private List<ProgressType> hiringProgress;
    @NotNull
    private int trainPay;
    private Integer pay;
    private String benefits;
    @NotNull
    private boolean militarySupport;
    @NotNull
    private String submitDocument;
    @NotNull
    private LocalDate startDate;
    @NotNull
    private LocalDate endDate;
    private String etc;

    @Getter
    @NoArgsConstructor
    public static class Area{
        @NotNull
        private List<Long> jobCodes;
        @NotNull
        private List<Long> techCodes;
        @NotNull
        private int hiring;
        @NotBlank
        private String majorTask;
    }
}
