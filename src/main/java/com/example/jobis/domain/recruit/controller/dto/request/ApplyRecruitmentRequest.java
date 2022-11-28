package com.example.jobis.domain.recruit.controller.dto.request;

import com.example.jobis.domain.recruit.domain.enums.ProgressType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.aspectj.apache.bcel.classfile.Code;

import java.time.LocalDate;
import java.util.List;

@Getter
@NoArgsConstructor
public class ApplyRecruitmentRequest {
    private List<Area> areas;
    private String preferentialTreatment;
    private int requiredGrade;
    private int workHours;
    private List<Long> requiredLicenses;
    private List<ProgressType> hiringProgress;
    private int trainPay;
    private int pay;
    private String benefits;
    private boolean military;
    private String submitDocumentUrl;
    private LocalDate startDate;
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
