package team.returm.jobis.global.error.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    INTERNAL_SERVER_ERROR(500, "Internal Server Error"),

    BAD_EMAIL(400, "Bad Email Domain"),
    INVALID_EXTENSION(400, "Invalid Extension File"),
    INVALID_DATE_FILTER_RANGE(400, "Invalid Date Filter"),
    APPLICATION_STATUS_CANNOT_CHANGE(400, "Application Status Cannot be changed"),
    INVALID_DATE(400, "Invalid Date"),

    UNVERIFIED_EMAIL(401, "Unverified Email"),
    BAD_AUTH_CODE(401, "Bad Auth Code"),
    EXPIRED_TOKEN(401, "Token Expired"),
    INVALID_TOKEN(401, "Invalid Token"),
    INVALID_PASSWORD(401, "invalid password"),
    INVALID_CODE(401, "invalid code"),
    INVALID_GRADE(401, "Invalid Grade"),
    INVALID_STUDENT(401, "Invalid Student"),

    COMPANY_MISMATCH(403, "Company Mismatch"),
    RECRUITMENT_CANNOT_DELETE(403, "Recruitment Cannot Deleted"),
    APPLICATION_CANNOT_DELETE(403, "Application Cannot Deleted"),
    FIELD_TRAIN_DATE_CANNOT_CHANGE(403, "Field Train Date Cannot Changed"),

    USER_NOT_FOUND(404, "User Not Found"),
    AUTH_CODE_NOT_FOUND(404, "AuthCode Not Found"),
    COMPANY_NOT_FOUND(404, "Company Not Found"),
    STUDENT_NOT_FOUND(404, "Student Not Found"),
    TEACHER_NOT_FOUND(404, "Teacher Not Found"),
    MAIL_SEND_FAIL(404, "Mail Send Fail"),
    FILE_NOT_FOUND(404, "File not Found"),
    CODE_NOT_FOUND(404, "Code Not Found"),
    RECRUIT_NOT_FOUND(404, "Recruit Not Found"),
    RECRUIT_AREA_NOT_FOUND(404, "Recruit Area Not Found"),
    RECRUIT_AREA_CODE_NOT_FOUND(404, "Recruit Area Code Not Found"),
    REFRESH_TOKEN_NOT_FOUND(404, "Refresh Token Not Found"),
    APPLICATION_NOT_FOUND(404, "Application Not Found"),
    BOOKMARK_NOT_FOUND(404, "Bookmark Not Found"),

    COMPANY_ALREADY_EXISTS(409, "Company Already Exists"),
    STUDENT_ALREADY_EXISTS(409, "Student Already Exists"),
    APPLICATION_ALREADY_EXISTS(409, "Application Already Exists");

    private final Integer status;
    private final String message;
}
