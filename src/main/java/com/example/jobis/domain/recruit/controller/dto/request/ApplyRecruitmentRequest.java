package com.example.jobis.domain.recruit.controller.dto.request;

import com.example.jobis.domain.recruit.domain.enums.ProgressType;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@Getter
@NoArgsConstructor
public class ApplyRecruitmentRequest {
    @NotNull(message = "모집분야는 null일 수 없습니다.")
    private List<Area> areas;
    private String preferentialTreatment;
    private int requiredGrade;
    private int workHours;
    private List<String> requiredLicenses;

    @NotNull(message = "채용절차 null일 수 없습니다.")
    private List<ProgressType> hiringProgress;
    @NotNull(message = "실습수당은 null 일 수 없습니다.")
    private int trainPay;
    private int pay;
    private String benefits;
    private boolean military;
    @NotNull(message = "제출 서류는 null일 수 없습니다.")
    private String submitDocument;
    @NotNull(message = "모집기간은 null일 수 없습니다.")
    private LocalDate startDate;
    @NotNull(message = "모집기간은 null일 수 없습니다.")
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
