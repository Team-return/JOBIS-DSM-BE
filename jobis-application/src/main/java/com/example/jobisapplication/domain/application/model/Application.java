package com.example.jobisapplication.domain.application.model;

import com.example.jobisapplication.common.annotation.Aggregate;
import com.example.jobisapplication.domain.application.exception.ApplicationCannotDeleteException;
import com.example.jobisapplication.domain.application.exception.ApplicationNotFoundException;
import com.example.jobisapplication.domain.application.exception.ApplicationStatusCannotChangeException;
import com.example.jobisapplication.domain.application.exception.InvalidStudentException;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

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

    public Application toFieldTrain(LocalDate startDate, LocalDate endDate) {
        checkApplicationStatus(this.applicationStatus, ApplicationStatus.PASS);

        return this;
    }

    public Application rejectApplication(String reason) {
        if (applicationStatus != ApplicationStatus.REQUESTED) {
            throw ApplicationStatusCannotChangeException.EXCEPTION;
        }
        
        return this.toBuilder()
                .applicationStatus(ApplicationStatus.REJECTED)
                .rejectionReason(reason)
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

    public void checkIsDeleteable(StudentEntity studentEntity) {
        if (!this.studentEntity.equals(studentEntity)) {
            throw InvalidStudentException.EXCEPTION;
        }

        if (this.applicationStatus != ApplicationStatus.REQUESTED) {
            throw ApplicationCannotDeleteException.EXCEPTION;
        }
    }
}