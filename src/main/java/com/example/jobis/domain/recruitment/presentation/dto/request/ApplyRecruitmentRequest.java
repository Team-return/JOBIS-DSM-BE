package com.example.jobis.domain.recruitment.presentation.dto.request;

import com.example.jobis.domain.recruitment.domain.enums.ProgressType;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
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
    @NoArgsConstructor
    public static class Area{
        @NotNull
        private List<Long> job;
        @NotNull
        private List<Long> tech;
        @NotNull
        private int hiring;
        @NotBlank
        private String majorTask;
    }
}
