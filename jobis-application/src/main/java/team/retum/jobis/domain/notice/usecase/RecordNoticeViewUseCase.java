package team.retum.jobis.domain.notice.usecase;

import lombok.RequiredArgsConstructor;
import team.retum.jobis.common.annotation.UseCase;
import team.retum.jobis.common.spi.SecurityPort;
import team.retum.jobis.domain.notice.exception.NoticeNotFoundException;
import team.retum.jobis.domain.notice.model.ViewLog;
import team.retum.jobis.domain.notice.spi.CommandViewLogPort;
import team.retum.jobis.domain.notice.spi.QueryNoticePort;
import team.retum.jobis.domain.notice.spi.QueryViewLogPort;

@RequiredArgsConstructor
@UseCase
public class RecordNoticeViewUseCase {

    private final QueryNoticePort queryNoticePort;
    private final QueryViewLogPort queryViewLogPort;
    private final CommandViewLogPort commandViewLogPort;
    private final SecurityPort securityPort;

    public void execute(Long noticeId) {
        queryNoticePort.getById(noticeId)
            .orElseThrow(() -> NoticeNotFoundException.EXCEPTION);

        Long studentId = securityPort.getCurrentUserId();

        boolean alreadyViewed = queryViewLogPort.existsByNoticeIdAndStudentId(noticeId, studentId);

        if (!alreadyViewed) {
            ViewLog viewLog = ViewLog.builder()
                .noticeId(noticeId)
                .studentId(studentId)
                .build();

            commandViewLogPort.save(viewLog);
        }
    }
}
