package team.retum.jobis.domain.recruitment.model;

import lombok.Builder;
import lombok.Getter;
import team.retum.jobis.common.annotation.Aggregate;
import team.retum.jobis.domain.recruitment.exception.CompanyMismatchException;
import team.retum.jobis.domain.recruitment.exception.InvalidRecruitmentStatusException;

import java.time.LocalDate;
import java.util.List;

@Getter
@Builder(toBuilder = true)
@Aggregate
public class Recruitment {

    private final Long id;

    private final Integer recruitYear;

    private final RecruitStatus status;

    private final String preferentialTreatment;

    private final List<String> requiredLicenses;

    private final Integer requiredGrade;

    private final Integer workingHours;

    private final String benefits;

    private final boolean militarySupport;

    private final List<ProgressType> hiringProgress;

    private final String submitDocument;

    private final String etc;

    private final LocalDate startDate;

    private final LocalDate endDate;

    private final Integer trainPay;

    private final Integer pay;

    private final boolean personalContract;

    private final Long companyId;

    public Recruitment update(Integer trainPay, Integer pay, int workingHours, String submitDocument,
                              LocalDate startDate, LocalDate endDate, String benefits, List<String> requiredLicenses,
                              boolean militarySupport, String etc, String preferentialTreatment, List<ProgressType> hiringProgress, Integer requiredGrade
    ) {
        return this.toBuilder()
                .workingHours(workingHours)
                .hiringProgress(hiringProgress)
                .submitDocument(submitDocument)
                .requiredGrade(requiredGrade)
                .benefits(benefits)
                .preferentialTreatment(preferentialTreatment)
                .startDate(startDate)
                .endDate(endDate)
                .trainPay(trainPay)
                .pay(pay)
                .requiredLicenses(requiredLicenses)
                .militarySupport(militarySupport)
                .etc(etc)
                .build();
    }

    public Recruitment changeStatus(RecruitStatus status) {
        return this.toBuilder()
                .status(status)
                .build();
    }

    public void checkCompany(Long companyId) {
        if (!this.companyId.equals(companyId)) {
            throw CompanyMismatchException.EXCEPTION;
        }
    }

    public void checkIsApplicable() {
        if (this.status != RecruitStatus.RECRUITING) {
            throw InvalidRecruitmentStatusException.EXCEPTION;
        }
    }
}
