package team.retum.jobis.domain.notice.exception;

import team.retum.jobis.common.error.JobisException;
import team.retum.jobis.domain.notice.exception.error.NoticeErrorCode;

public class NoticeNotFoundException extends JobisException {

    public static final JobisException EXCEPTION = new NoticeNotFoundException();

    private NoticeNotFoundException() {
        super(NoticeErrorCode.NOTICE_NOT_FOUND);
    }
}
