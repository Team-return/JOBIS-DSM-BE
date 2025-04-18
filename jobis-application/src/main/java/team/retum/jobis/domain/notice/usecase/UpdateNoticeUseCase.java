package team.retum.jobis.domain.notice.usecase;

import lombok.RequiredArgsConstructor;
import team.retum.jobis.common.annotation.UseCase;
import team.retum.jobis.domain.notice.exception.NoticeNotFoundException;
import team.retum.jobis.domain.notice.model.Notice;
import team.retum.jobis.domain.notice.spi.CommandNoticePort;
import team.retum.jobis.domain.notice.spi.QueryNoticePort;

@RequiredArgsConstructor
@UseCase
public class UpdateNoticeUseCase {

    private final CommandNoticePort commandNoticePort;
    private final QueryNoticePort queryNoticePort;

    public void execute(String title, String content, Long noticeId) {
        Notice notice = queryNoticePort.getById(noticeId)
            .orElseThrow(() -> NoticeNotFoundException.EXCEPTION);

        commandNoticePort.save(
            notice.update(
                title,
                content
            )
        );
    }
}
