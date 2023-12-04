package team.retum.jobis.domain.recruitment.presentation.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import team.retum.jobis.domain.recruitment.dto.request.UpdateRecruitmentRequest;
import team.retum.jobis.domain.recruitment.model.ProgressType;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Getter
@NoArgsConstructor
public class UpdateRecruitmentWebRequest {

    private Integer requiredGrade;

    @NotNull
    private LocalTime startTime;

    @NotNull
    private LocalTime endTime;

    private List<String> requiredLicenses;

    @NotNull
    private List<ProgressType> hiringProgress;

    @NotNull
    private int trainPay;

    private String pay;

    @Size(max = 550)
    private String benefits;

    @NotNull
    private boolean military;

    @Size(max = 100)
    @NotNull
    private String submitDocument;

    @NotNull
    private LocalDate startDate;

    @NotNull
    private LocalDate endDate;

    @Size(max = 350)
    private String etc;

    public UpdateRecruitmentRequest toDomainRequest() {
        return UpdateRecruitmentRequest.builder()
                .requiredGrade(this.requiredGrade)
                .startTime(this.startTime)
                .endTime(this.endTime)
                .requiredLicenses(this.requiredLicenses)
                .hiringProgress(this.hiringProgress)
                .trainPay(this.trainPay)
                .pay(this.pay)
                .benefits(this.benefits)
                .military(this.military)
                .submitDocument(this.submitDocument)
                .startDate(this.startDate)
                .endDate(this.endDate)
                .etc(this.etc)
                .build();
    }
}
