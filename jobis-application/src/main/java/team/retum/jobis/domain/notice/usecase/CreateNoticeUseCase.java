package team.retum.jobis.domain.notice.usecase;

import lombok.RequiredArgsConstructor;
import team.retum.jobis.common.annotation.UseCase;
import team.retum.jobis.common.spi.PublishEventPort;
import team.retum.jobis.domain.notice.dto.request.CreateNoticeRequest;
import team.retum.jobis.domain.notice.event.NoticePostedEvent;
import team.retum.jobis.domain.notice.model.Notice;
import team.retum.jobis.domain.notice.model.NoticeAttachment;
import team.retum.jobis.domain.notice.spi.CommandNoticePort;

import java.util.List;

@RequiredArgsConstructor
@UseCase
public class CreateNoticeUseCase {

    private final CommandNoticePort commandNoticePort;
    private final PublishEventPort eventPublisher;

    public void execute(CreateNoticeRequest request) {
        List<NoticeAttachment> attachments = request.getAttachments().stream()
            .filter(attachment -> attachment.getUrl() != null && attachment.getType() != null)
            .map(attachment -> new NoticeAttachment(attachment.getUrl(), attachment.getType()))
            .toList();

        Notice savedNotice = commandNoticePort.save(Notice.builder()
            .title(request.getTitle())
            .content(request.getContent())
            .attachments(attachments)
            .build());

        eventPublisher.publishEvent(new NoticePostedEvent(savedNotice));
    }
}
