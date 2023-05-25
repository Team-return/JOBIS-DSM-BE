package team.returm.jobis.domain.recruitment.exception.error;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import team.returm.jobis.global.error.ErrorProperty;

@Getter
@AllArgsConstructor
public enum RecruitmentErrorCode implements ErrorProperty {

    RECRUITMENT_CANNOT_DELETE(HttpStatus.FORBIDDEN, "Recruitment Cannot Deleted"),
    COMPANY_MISMATCH(HttpStatus.FORBIDDEN, "Company Mismatch"),

    RECRUITMENT_NOT_FOUND(HttpStatus.NOT_FOUND, "Recruitment Not Found"),
    RECRUIT_AREA_NOT_FOUND(HttpStatus.NOT_FOUND, "Recruit Area Not Found");


    private final HttpStatus status;
    private final String message;
}
