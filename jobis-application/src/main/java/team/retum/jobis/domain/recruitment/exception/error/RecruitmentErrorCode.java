package team.retum.jobis.domain.recruitment.exception.error;

import team.retum.jobis.common.error.ErrorProperty;
import team.retum.jobis.common.error.HttpStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RecruitmentErrorCode implements ErrorProperty {

    INVALID_RECRUITMENT_STATUS(HttpStatus.BAD_REQUEST, "Invalid Recruitment Status"),

    RECRUIT_AREA_CANNOT_DELETE(HttpStatus.FORBIDDEN, "RecruitArea Cannot Delete"),
    RECRUITMENT_CANNOT_DELETE(HttpStatus.FORBIDDEN, "Recruitment Cannot Deleted"),
    COMPANY_MISMATCH(HttpStatus.FORBIDDEN, "Company Mismatch"),

    RECRUITMENT_NOT_FOUND(HttpStatus.NOT_FOUND, "Recruitment Not Found"),
    RECRUIT_AREA_NOT_FOUND(HttpStatus.NOT_FOUND, "Recruit Area Not Found"),

    RECRUITMENT_ALREADY_EXISTS(HttpStatus.CONFLICT, "Recruitment Already Exists");

    private final HttpStatus status;
    private final String message;
}
