package team.retum.jobis.domain.application.model;

import lombok.Builder;
import lombok.Getter;
import team.retum.jobis.common.annotation.Aggregate;
import team.retum.jobis.domain.application.exception.ApplicationCannotDeleteException;
import team.retum.jobis.domain.application.exception.ApplicationNotFoundException;
import team.retum.jobis.domain.application.exception.ApplicationStatusCannotChangeException;
import team.retum.jobis.domain.application.exception.InvalidStudentException;
import team.retum.jobis.domain.student.model.Student;

import java.time.LocalDate;
import java.util.List;

@Getter
@Builder(toBuilder = true)
@Aggregate
public class Application {

    private final Long id;

    private final Long studentId;

    private final Long recruitmentId;

    private final ApplicationStatus applicationStatus;

    private final String rejectionReason;

    private final LocalDate startDate;

    private final LocalDate endDate;

    private final List<ApplicationAttachment> attachments;

    public Application toFieldTrain(LocalDate startDate, LocalDate endDate) {
        checkApplicationStatus(this.applicationStatus, ApplicationStatus.PASS);

        return this.toBuilder()
                .applicationStatus(ApplicationStatus.FIELD_TRAIN)
                .startDate(startDate)
                .endDate(endDate)
                .build();
    }

    public Application rejectApplication(String reason) {
        return this.toBuilder()
                .applicationStatus(ApplicationStatus.REJECTED)
                .rejectionReason(reason)
                .build();
    }

    public Application reapply(List<ApplicationAttachment> attachments) {
        return this.toBuilder()
                .attachments(attachments)
                .applicationStatus(ApplicationStatus.REQUESTED)
                .build();
    }

    public void checkReviewAuthority() {
        if (this.applicationStatus == ApplicationStatus.REQUESTED
                || this.applicationStatus == ApplicationStatus.APPROVED
                || this.applicationStatus == ApplicationStatus.REJECTED) {
            throw ApplicationNotFoundException.EXCEPTION;
        }
    }

    public void checkApplicationStatus(ApplicationStatus status1, ApplicationStatus status2) {
        if (status1 != status2) {
            throw ApplicationStatusCannotChangeException.EXCEPTION;
        }
    }


    public void checkIsDeletable(Student student) {
        if (!this.studentId.equals(student.getId())) {
            throw InvalidStudentException.EXCEPTION;
        }

        if (this.applicationStatus != ApplicationStatus.REQUESTED) {
            throw ApplicationCannotDeleteException.EXCEPTION;
        }
    }
}
