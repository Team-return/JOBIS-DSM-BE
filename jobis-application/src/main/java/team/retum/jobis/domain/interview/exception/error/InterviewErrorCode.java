package team.retum.jobis.domain.interview.exception.error;

import lombok.AllArgsConstructor;
import lombok.Getter;
import team.retum.jobis.common.error.ErrorProperty;
import team.retum.jobis.common.error.HttpStatus;

@Getter
@AllArgsConstructor
public enum InterviewErrorCode implements ErrorProperty {

    INVALID_INTERVIEW_DATE(HttpStatus.BAD_REQUEST, "Invalid Interview Date"),
    INVALID_INTERVIEW_TYPE(HttpStatus.BAD_REQUEST, "Invalid Interview Type"),
    INVALID_STUDENT_ID(HttpStatus.BAD_REQUEST, "Invalid Student Id"),
    DOCUMENT_NUMBER_NOT_FOUND(HttpStatus.NOT_FOUND, "Document Number Not Found"),
    DOCUMENT_NUMBER_ALREADY_EXISTS(HttpStatus.CONFLICT, "Document Number Already Exists"),
    INTERVIEW_NOT_FOUND(HttpStatus.NOT_FOUND, "Interview Not Found");

    private final HttpStatus status;
    private final String message;
}
