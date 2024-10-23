package team.retum.jobis.domain.recruitment.presentation.dto.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import team.retum.jobis.domain.recruitment.dto.request.UpdateRecruitmentRequest;
import team.retum.jobis.domain.recruitment.model.ProgressType;
import team.retum.jobis.global.exception.BadRequestException;
import team.retum.jobis.global.util.RegexProperty;

import java.time.LocalDate;
import java.util.List;
import java.util.regex.Pattern;

@Getter
@NoArgsConstructor
public class UpdateRecruitmentWebRequest {

    private Integer requiredGrade;

    @NotNull
    private String workingHours;

    @NotNull
    private Boolean flexibleWorking;

    private List<String> requiredLicenses;

    @NotNull
    private List<ProgressType> hiringProgress;

    @NotNull
    @Max(100000000)
    private Integer trainPay;

    @Size(max = 20)
    private String pay;

    @Size(max = 550)
    private String benefits;

    @NotNull
    private Boolean military;

    @Size(max = 100)
    @NotNull
    private String submitDocument;

    private LocalDate startDate;

    private LocalDate endDate;

    private Boolean convertible;

    @Size(max = 350)
    private String etc;

    public UpdateRecruitmentRequest toDomainRequest() {
        if (!flexibleWorking && !Pattern.matches(RegexProperty.WORKING_HOURS, workingHours)) {
            throw BadRequestException.EXCEPTION;
        }

        return UpdateRecruitmentRequest.builder()
            .requiredGrade(this.requiredGrade)
            .workingHours(this.workingHours)
            .flexibleWorking(this.flexibleWorking)
            .requiredLicenses(this.requiredLicenses)
            .hiringProgress(this.hiringProgress)
            .trainPay(this.trainPay)
            .pay(this.pay)
            .benefits(this.benefits)
            .military(this.military)
            .submitDocument(this.submitDocument)
            .startDate(this.startDate)
            .endDate(this.endDate)
            .convertible(this.convertible)
            .etc(this.etc)
            .build();
    }
}
