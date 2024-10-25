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
import java.time.LocalDateTime;
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

    private final String workingHours;

    private final boolean flexibleWorking;

    private final String benefits;

    private final boolean militarySupport;

    private final List<ProgressType> hiringProgress;

    private final String submitDocument;

    private final String etc;

    private final RecruitingPeriod recruitingPeriod;

    private final Salary salary;

    private final boolean personalContract;

    private final boolean winterIntern;

    private final Boolean hireConvertible;

    private final Long companyId;

    private final LocalDateTime createdAt;

    public static Recruitment of(ApplyRecruitmentRequest request, Long currentCompanyId) {
        return Recruitment.builder()
            .companyId(currentCompanyId)
            .recruitYear(Year.now().getValue())
            .militarySupport(request.militarySupport())
            .personalContract(request.personalContact())
            .workingHours(request.workingHours())
            .flexibleWorking(request.flexibleWorking())
            .salary(new Salary(request.trainPay(), request.pay()))
            .requiredLicenses(request.requiredLicenses())
            .status(RecruitStatus.REQUESTED)
            .requiredGrade(request.requiredGrade())
            .recruitingPeriod(new RecruitingPeriod(request.startDate(), request.endDate()))
            .submitDocument(request.submitDocument())
            .benefits(request.benefits())
            .etc(request.etc())
            .hiringProgress(request.hiringProgress())
            .winterIntern(request.winterIntern())
            .hireConvertible(request.hireConvertible())
            .build();
    }

    public Recruitment changeStatus(RecruitStatus status) {
        return this.toBuilder()
            .status(status)
            .build();
    }

    public void checkIsApplicable(Integer entranceYear) {
        if (this.status != RecruitStatus.RECRUITING) {
            throw InvalidRecruitmentStatusException.EXCEPTION;
        }

        // 졸업생이 지원시 예외를 던짐
        if (Year.now().getValue() - 3 >= entranceYear) {
            throw InvalidGradeException.EXCEPTION;
        }

        // 1학년이 지원할 경우 예외를 던짐
        if (Year.now().getValue() == entranceYear) {
            throw InvalidGradeException.EXCEPTION;
        }

        // 2학년이 아닌 학년이 채험형 현장실습 지원시 예외를 던짐
        if (this.winterIntern && entranceYear != Year.now().getValue() - 1) {
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

        if (this.status == RecruitStatus.REQUESTED || this.status == RecruitStatus.DONE) {
            return this;
        }

        if (DateUtil.between(today, recruitingPeriod.startDate(), recruitingPeriod.endDate())) {
            return this.changeStatus(RecruitStatus.RECRUITING);
        }

        if (today.equals(recruitingPeriod.endDate())) {
            return this.changeStatus(RecruitStatus.DONE);
        }

        return this;
    }

    public Recruitment update(UpdateRecruitmentRequest request) {
        return this.toBuilder()
            .requiredGrade(request.requiredGrade())
            .workingHours(request.workingHours())
            .flexibleWorking(request.flexibleWorking())
            .requiredLicenses(request.requiredLicenses())
            .hiringProgress(request.hiringProgress())
            .salary(new Salary(request.trainPay(), request.pay()))
            .benefits(request.benefits())
            .militarySupport(request.military())
            .submitDocument(request.submitDocument())
            .recruitingPeriod(new RecruitingPeriod(request.startDate(), request.endDate()))
            .hireConvertible(request.hireConvertible())
            .etc(request.etc())
            .build();
    }
}
