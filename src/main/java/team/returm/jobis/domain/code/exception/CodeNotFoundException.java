package team.returm.jobis.domain.code.exception;

import team.returm.jobis.global.error.exception.ErrorCode;
import team.returm.jobis.global.error.exception.JobisException;

public class CodeNotFoundException extends JobisException {

    public static final JobisException EXCEPTION
            = new CodeNotFoundException();

    private CodeNotFoundException() {
        super(ErrorCode.CODE_NOT_FOUND);
    }
}
