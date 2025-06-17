package team.retum.jobis.domain.notice.usecase;

import lombok.RequiredArgsConstructor;
import team.retum.jobis.common.annotation.UseCase;
import team.retum.jobis.common.spi.PublishEventPort;
import team.retum.jobis.domain.notice.event.NoticeUpdatedEvent;
import team.retum.jobis.domain.notice.exception.NoticeNotFoundException;
import team.retum.jobis.domain.notice.model.Notice;
import team.retum.jobis.domain.notice.spi.CommandNoticePort;
import team.retum.jobis.domain.notice.spi.QueryNoticePort;

@RequiredArgsConstructor
@UseCase
public class UpdateNoticeUseCase {

    private final CommandNoticePort commandNoticePort;
    private final QueryNoticePort queryNoticePort;
    private final PublishEventPort eventPublisher;

    public void execute(String title, String content, Long noticeId) {
        Notice notice = queryNoticePort.getById(noticeId)
            .orElseThrow(() -> NoticeNotFoundException.EXCEPTION);

        Notice updatedNotice = commandNoticePort.save(
            notice.update(
                title,
                content
            )
        );

        eventPublisher.publishEvent(new NoticeUpdatedEvent(updatedNotice));
    }
}
