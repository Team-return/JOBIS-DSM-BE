package team.retum.jobis.domain.code.exception;

import team.retum.jobis.common.error.JobisException;
import team.retum.jobis.domain.code.exception.error.CodeErrorCode;

public class CodeNotFoundException extends JobisException {

    public static final JobisException EXCEPTION = new CodeNotFoundException();

    private CodeNotFoundException() {
        super(CodeErrorCode.CODE_NOT_FOUND);
    }
}
