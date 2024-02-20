package team.retum.jobis.domain.recruitment.model;

import lombok.Builder;
import lombok.Getter;
import team.retum.jobis.common.annotation.Aggregate;
import team.retum.jobis.common.util.DateUtil;
import team.retum.jobis.domain.application.exception.InvalidGradeException;
import team.retum.jobis.domain.recruitment.dto.request.ApplyRecruitmentRequest;
import team.retum.jobis.domain.recruitment.dto.request.UpdateRecruitmentRequest;
import team.retum.jobis.domain.recruitment.exception.InvalidRecruitmentStatusException;
import team.retum.jobis.domain.recruitment.exception.RecruitmentCannotDeleteException;

import java.time.LocalDate;
import java.time.Year;
import java.util.List;

@Getter
@Builder(toBuilder = true)
@Aggregate
public class Recruitment {

    private final Long id;

    private final Integer recruitYear;

    private final RecruitStatus status;

    private final List<String> requiredLicenses;

    private final Integer requiredGrade;

    private final WorkingHours workingHours;

    private final String benefits;

    private final boolean militarySupport;

    private final List<ProgressType> hiringProgress;

    private final String submitDocument;

    private final String etc;

    private final RecruitingPeriod recruitingPeriod;

    private final Salary salary;

    private final boolean personalContract;

    private final boolean winterIntern;

    private final Long companyId;

    public static Recruitment of(ApplyRecruitmentRequest request, Long currentCompanyId) {
        return Recruitment.builder()
                .companyId(currentCompanyId)
                .recruitYear(Year.now().getValue())
                .militarySupport(request.isMilitarySupport())
                .personalContract(request.isPersonalContact())
                .workingHours(new WorkingHours(request.getStartTime(), request.getEndTime()))
                .salary(new Salary(request.getTrainPay(), request.getPay()))
                .requiredLicenses(request.getRequiredLicenses())
                .status(RecruitStatus.REQUESTED)
                .requiredGrade(request.getRequiredGrade())
                .recruitingPeriod(new RecruitingPeriod(request.getStartDate(), request.getEndDate()))
                .submitDocument(request.getSubmitDocument())
                .benefits(request.getBenefits())
                .etc(request.getEtc())
                .hiringProgress(request.getHiringProgress())
                .winterIntern(request.isWinterIntern())
                .build();
    }

    public Recruitment changeStatus(RecruitStatus status) {
        return this.toBuilder()
                .status(status)
                .build();
    }

    public void checkIsApplicable(Integer studentGrade) {
        if (this.status != RecruitStatus.RECRUITING) {
            throw InvalidRecruitmentStatusException.EXCEPTION;
        }

        if (studentGrade == 1 || (!this.winterIntern && studentGrade == 2)) {
            throw InvalidGradeException.EXCEPTION;
        }
    }

    public void checkIsDeletable() {
        if (this.status.equals(RecruitStatus.RECRUITING)) {
            throw RecruitmentCannotDeleteException.EXCEPTION;
        }
    }

    public Recruitment updateRecruitmentStatus() {
        LocalDate today = LocalDate.now();
        RecruitingPeriod recruitingPeriod = this.recruitingPeriod;

        if (this.status == RecruitStatus.REQUESTED) {
            return this;
        }

        if (DateUtil.between(today, recruitingPeriod.startDate(), recruitingPeriod.endDate()) &&
                this.status != RecruitStatus.DONE
        ) {
            return this.changeStatus(RecruitStatus.RECRUITING);
        }

        if (today.isAfter(recruitingPeriod.endDate())) {
            return this.changeStatus(RecruitStatus.DONE);
        }

        return this;
    }

    public Recruitment update(UpdateRecruitmentRequest request) {
        return this.toBuilder()
                .requiredGrade(request.getRequiredGrade())
                .workingHours(new WorkingHours(request.getStartTime(), request.getEndTime()))
                .requiredLicenses(request.getRequiredLicenses())
                .hiringProgress(request.getHiringProgress())
                .salary(new Salary(request.getTrainPay(), request.getPay()))
                .benefits(request.getBenefits())
                .militarySupport(request.isMilitary())
                .submitDocument(request.getSubmitDocument())
                .recruitingPeriod(new RecruitingPeriod(request.getStartDate(), request.getEndDate()))
                .etc(request.getEtc())
                .build();
    }

}
