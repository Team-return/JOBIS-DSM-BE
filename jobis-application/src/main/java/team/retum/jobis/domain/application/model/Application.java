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
import java.time.LocalDateTime;
import java.util.Collections;
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

    private final List<ApplicationRejectionAttachment> applicationRejectionAttachments;

    private final LocalDateTime createdAt;

    private final LocalDateTime updatedAt;

    public static void checkApplicationStatus(ApplicationStatus requestedStatus, ApplicationStatus... baseStatuses) {
        if (!List.of(baseStatuses).contains(requestedStatus)) {
            throw ApplicationStatusCannotChangeException.EXCEPTION;
        }
    }

    public Application toFieldTrain(LocalDate startDate, LocalDate endDate) {
        checkApplicationStatus(this.applicationStatus, ApplicationStatus.PASS);

        return this.toBuilder()
            .applicationStatus(ApplicationStatus.FIELD_TRAIN)
            .startDate(startDate)
            .endDate(endDate)
            .build();
    }

    public Application rejectApplication(String reason, List<ApplicationRejectionAttachment> applicationRejectionAttachments) {
        return this.toBuilder()
            .applicationStatus(ApplicationStatus.REJECTED)
            .rejectionReason(reason)
            .applicationRejectionAttachments(applicationRejectionAttachments)
            .attachments(Collections.emptyList())
            .build();
    }

    public Application rejectApplication(String reason) {
         return this.toBuilder()
            .applicationStatus(ApplicationStatus.REJECTED)
            .rejectionReason(reason)
            .applicationRejectionAttachments(Collections.emptyList())
            .attachments(Collections.emptyList())
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

    public void checkIsDeletable(Student student) {
        if (!this.studentId.equals(student.getId())) {
            throw InvalidStudentException.EXCEPTION;
        }

        if (this.applicationStatus != ApplicationStatus.REQUESTED) {
            throw ApplicationCannotDeleteException.EXCEPTION;
        }
    }
}
