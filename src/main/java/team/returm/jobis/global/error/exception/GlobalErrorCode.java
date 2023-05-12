package team.returm.jobis.global.error.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import team.returm.jobis.global.error.ErrorProperty;

@Getter
@AllArgsConstructor
public enum GlobalErrorCode implements ErrorProperty {
    INTERNAL_SERVER_ERROR(500, "Internal Server Error"),

    BAD_EMAIL(400, "Bad Email Domain"),
    INVALID_EXTENSION(400, "Invalid Extension File"),
    INVALID_DATE_FILTER_RANGE(400, "Invalid Date Filter"),

    UNVERIFIED_EMAIL(401, "Unverified Email"),
    BAD_AUTH_CODE(401, "Bad Auth Code"),
    EXPIRED_TOKEN(401, "Token Expired"),
    INVALID_TOKEN(401, "Invalid Token"),
    INVALID_PASSWORD(401, "invalid password"),

    COMPANY_MISMATCH(403, "Company Mismatch"),
    RECRUITMENT_CANNOT_DELETE(403, "Recruitment Cannot Deleted"),

    USER_NOT_FOUND(404, "User Not Found"),
    COMPANY_NOT_FOUND(404, "Company Not Found"),
    STUDENT_NOT_FOUND(404, "Student Not Found"),
    TEACHER_NOT_FOUND(404, "Teacher Not Found"),
    MAIL_SEND_FAIL(404, "Mail Send Fail"),
    FILE_NOT_FOUND(404, "File not Found"),
    RECRUIT_NOT_FOUND(404, "Recruit Not Found"),
    RECRUIT_AREA_NOT_FOUND(404, "Recruit Area Not Found"),

    COMPANY_ALREADY_EXISTS(409, "Company Already Exists"),
    STUDENT_ALREADY_EXISTS(409, "Student Already Exists");

    private final Integer status;
    private final String message;
}
