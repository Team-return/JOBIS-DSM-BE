package com.example.jobis.domain.recruitment.controller.dto.request;

import com.example.jobis.domain.recruitment.domain.enums.ProgressType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@Getter
@NoArgsConstructor
public class ApplyRecruitmentRequest {
    @NotNull
    private List<Area> areas;
    private String preferentialTreatment;
    private Integer requiredGrade;

    @NotNull
    private Integer workHours;
    private List<String> requiredLicenses;

    @NotNull
    private List<ProgressType> hiringProgress;
    @NotNull
    private Integer trainPay;
    private Integer pay;
    private String benefits;

    @NotNull
    private Boolean militarySupport;
    @NotNull
    private String submitDocument;
    @NotNull
    private LocalDate startDate;
    @NotNull
    private LocalDate endDate;
    private String etc;

    @Getter
    public static class Area{
        private List<Long> job;
        private List<Long> tech;
        private int hiring;
        private String majorTask;
    }
}
