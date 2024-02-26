package team.retum.jobis.domain.notice.usecase;

import lombok.RequiredArgsConstructor;
import team.retum.jobis.common.annotation.UseCase;
import team.retum.jobis.domain.notice.dto.request.CreateNoticeRequest;
import team.retum.jobis.domain.notice.model.Notice;
import team.retum.jobis.domain.notice.model.NoticeAttachment;
import team.retum.jobis.domain.notice.spi.CommandNoticePort;

import java.util.List;

@RequiredArgsConstructor
@UseCase
public class CreateNoticeUseCase {

    private final CommandNoticePort commandNoticePort;
    public void execute(CreateNoticeRequest request) {

        List<NoticeAttachment> attachments = request.getAttachments()
                .stream()
                .map(attachment -> new NoticeAttachment(attachment.getUrl(), attachment.getType()))
                .toList();

        commandNoticePort.saveNotice(
                Notice.builder()
                        .title(request.getTitle())
                        .content(request.getContent())
                        .attachments(attachments)
                        .build()
        );
    }
}
