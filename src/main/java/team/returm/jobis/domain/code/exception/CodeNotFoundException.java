package team.returm.jobis.domain.code.exception;

import team.returm.jobis.global.error.exception.GlobalErrorCode;
import team.returm.jobis.global.error.exception.JobisException;

public class CodeNotFoundException extends JobisException {

    public static final JobisException EXCEPTION
            = new CodeNotFoundException();

    private CodeNotFoundException() {
        super(GlobalErrorCode.CODE_NOT_FOUND);
    }
}
