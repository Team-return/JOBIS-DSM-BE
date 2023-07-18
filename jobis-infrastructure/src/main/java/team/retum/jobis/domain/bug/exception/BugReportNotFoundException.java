package team.retum.jobis.domain.bug.exception;

import team.retum.jobis.domain.bug.exception.error.BugErrorCode;
import com.example.jobisapplication.common.error.JobisException;

public class BugReportNotFoundException extends JobisException {

    public static final JobisException EXCEPTION = new BugReportNotFoundException();

    private BugReportNotFoundException() {
        super(BugErrorCode.BUG_REPORT_NOT_FOUND);
    }
}
