package team.retum.jobis.domain.recruitment.presentation.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.PatternMatchUtils;
import team.retum.jobis.domain.recruitment.dto.request.ApplyRecruitmentRequest;
import team.retum.jobis.domain.recruitment.model.ProgressType;
import team.retum.jobis.global.annotation.ValidListElements;
import team.retum.jobis.global.exception.BadRequestException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Getter
@NoArgsConstructor
public class ApplyRecruitmentWebRequest {

    @Valid
    @ValidListElements
    private List<RecruitAreaWebRequest> areas;

    private Integer requiredGrade;

    @NotBlank
    private String workingHours;

    @NotNull
    private Boolean flexibleWorking;

    private List<@NotNull String> requiredLicenses;

    @ValidListElements
    private List<ProgressType> hiringProgress;

    @NotNull
    private Integer trainPay;

    private String pay;

    @Size(max = 550)
    private String benefits;

    @NotNull
    private Boolean militarySupport;

    @NotNull
    private Boolean personalContact;

    @Size(max = 100)
    @NotNull
    private String submitDocument;

    private LocalDate startDate;

    private LocalDate endDate;

    @NotNull
    private Boolean winterIntern;

    @Size(max = 350)
    private String etc;

    public ApplyRecruitmentRequest toDomainRequest() {
        if (!flexibleWorking && !PatternMatchUtils.simpleMatch("/^([01][0-9]|2[0-3]):([0-5][0-9]) ~ ([01][0-9]|2[0-3]):([0-5][0-9])$/", workingHours)) {
            throw BadRequestException.EXCEPTION;
        }

        return ApplyRecruitmentRequest.builder()
                .areas(this.areas.stream().map(RecruitAreaWebRequest::toDomainRequest).toList())
                .requiredGrade(this.requiredGrade)
                .workingHours(this.workingHours)
                .requiredLicenses(this.requiredLicenses)
                .hiringProgress(this.hiringProgress)
                .trainPay(this.trainPay)
                .pay(this.pay)
                .benefits(this.benefits)
                .militarySupport(this.militarySupport)
                .personalContact(this.personalContact)
                .submitDocument(this.submitDocument)
                .startDate(this.startDate)
                .endDate(this.endDate)
                .winterIntern(this.winterIntern)
                .etc(this.etc)
                .build();
    }
}
