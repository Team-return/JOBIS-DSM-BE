package team.retum.jobis.domain.recruitment.presentation.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import team.retum.jobis.domain.recruitment.dto.request.ApplyRecruitmentRequest;
import team.retum.jobis.domain.recruitment.model.ProgressType;
import team.retum.jobis.global.annotation.ValidListElements;
import team.retum.jobis.global.exception.BadRequestException;
import team.retum.jobis.global.util.RegexProperty;

import java.time.LocalDate;
import java.util.List;
import java.util.regex.Pattern;

@Getter
@NoArgsConstructor
public class ApplyRecruitmentWebRequest {

    @Valid
    @ValidListElements
    private List<RecruitAreaWebRequest> areas;

    @Size(max = 3000)
    private String additionalQualifications;

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

    private Boolean hireConvertible;

    @Size(max = 350)
    private String etc;

    private Boolean integrationPlan;

    public ApplyRecruitmentRequest toDomainRequest() {
        if (!flexibleWorking && !Pattern.matches(RegexProperty.WORKING_HOURS, workingHours)) {
            throw BadRequestException.EXCEPTION;
        }

        return ApplyRecruitmentRequest.builder()
            .areas(this.areas.stream().map(RecruitAreaWebRequest::toDomainRequest).toList())
            .additionalQualifications(this.additionalQualifications)
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
            .hireConvertible(this.hireConvertible)
            .etc(this.etc)
            .integrationPlan(this.integrationPlan)
            .build();
    }
}
