package com.example.jobis.domain.recruit.controller.dto.request;

import com.example.jobis.domain.recruit.domain.enums.ProgressType;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@Getter
@NoArgsConstructor
public class UpdateRecruitmentRequest {

    private String preferentialTreatment;
    private Integer requiredGrade;

    @NotNull(message = "work_hours는 null일 수 없습니다.")
    private Integer workHours;
    private List<String> requiredLicenses;

    @NotNull(message = "hiring_progress는 null일 수 없습니다.")
    private List<ProgressType> hiringProgress;
    @NotNull(message = "train_pay는 null 일 수 없습니다.")
    private Integer trainPay;
    private Integer pay;
    private String benefits;

    @NotNull(message = "military는 null일 수 없습니다.")
    private boolean military;
    @NotNull(message = "submit_document는 null일 수 없습니다.")
    private String submitDocument;
    @NotNull(message = "start_date는 null일 수 없습니다.")
    private LocalDate startDate;
    @NotNull(message = "end_date는 null일 수 없습니다.")
    private LocalDate endDate;
    private String etc;
}
