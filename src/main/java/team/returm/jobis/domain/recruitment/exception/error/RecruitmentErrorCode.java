package team.returm.jobis.domain.recruitment.exception.error;

import lombok.AllArgsConstructor;
import lombok.Getter;
import team.returm.jobis.global.error.ErrorProperty;

@Getter
@AllArgsConstructor
public enum RecruitmentErrorCode implements ErrorProperty {

    RECRUITMENT_CANNOT_DELETE(403, "Recruitment Cannot Deleted"),
    COMPANY_MISMATCH(403, "Company Mismatch"),

    RECRUITMENT_NOT_FOUND(404, "Recruitment Not Found"),
    RECRUIT_AREA_NOT_FOUND(404, "Recruit Area Not Found");


    private final int status;
    private final String message;
}
