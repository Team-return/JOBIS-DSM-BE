package team.retum.jobis.domain.notice.usecase;

import lombok.RequiredArgsConstructor;
import team.retum.jobis.common.annotation.UseCase;
import team.retum.jobis.common.spi.PublishEventPort;
import team.retum.jobis.domain.notice.dto.request.CreateNoticeRequest;
import team.retum.jobis.domain.notice.event.NoticePostedEvent;
import team.retum.jobis.domain.notice.model.Notice;
import team.retum.jobis.domain.notice.model.NoticeAttachment;
import team.retum.jobis.domain.notice.spi.CommandNoticePort;

@RequiredArgsConstructor
@UseCase
public class CreateNoticeUseCase {

    private final CommandNoticePort commandNoticePort;
    private final PublishEventPort publishEventPort;

    public void execute(CreateNoticeRequest request) {
        Notice savedNotice = commandNoticePort.saveNotice(Notice.builder()
            .title(request.getTitle())
            .content(request.getContent())
            .attachments(request.getAttachments().stream()
                .map(attachment -> new NoticeAttachment(attachment.getUrl(), attachment.getType()))
                .toList())
            .build());

        publishEventPort.publishEvent(new NoticePostedEvent(savedNotice));
    }
}
