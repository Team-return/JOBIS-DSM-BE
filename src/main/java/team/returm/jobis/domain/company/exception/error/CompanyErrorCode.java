package team.returm.jobis.domain.company.exception.error;

import lombok.AllArgsConstructor;
import lombok.Getter;
import team.returm.jobis.global.error.ErrorProperty;

@Getter
@AllArgsConstructor
public enum CompanyErrorCode implements ErrorProperty {

    COMPANY_NOT_FOUND(404, "Company Not Found"),

    COMPANY_ALREADY_EXISTS(409, "Company Already Exists");

    private final int status;
    private final String message;
}
