package team.retum.jobis.domain.notice.usecase;

import lombok.RequiredArgsConstructor;
import team.retum.jobis.common.annotation.UseCase;
import team.retum.jobis.domain.notice.exception.NoticeNotFoundException;
import team.retum.jobis.domain.notice.model.Notice;
import team.retum.jobis.domain.notice.spi.CommandNoticePort;
import team.retum.jobis.domain.notice.spi.QueryNoticePort;

@RequiredArgsConstructor
@UseCase
public class DeleteNoticeUseCase {

    private final CommandNoticePort commandNoticePort;
    private final QueryNoticePort queryNoticePort;

    public void execute(Long noticeId) {
        Notice notice = queryNoticePort.getNoticeById(noticeId)
            .orElseThrow(() -> NoticeNotFoundException.EXCEPTION);

        commandNoticePort.deleteNotice(notice);
    }
}
