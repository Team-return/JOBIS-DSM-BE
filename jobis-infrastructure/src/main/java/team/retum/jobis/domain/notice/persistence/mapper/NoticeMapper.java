package team.retum.jobis.domain.notice.persistence.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import team.retum.jobis.domain.notice.model.Notice;
import team.retum.jobis.domain.notice.model.NoticeAttachment;
import team.retum.jobis.domain.notice.persistence.entity.NoticeAttachmentEntity;
import team.retum.jobis.domain.notice.persistence.entity.NoticeEntity;
import team.retum.jobis.domain.notification.exception.NotificationNotFoundException;
import team.retum.jobis.domain.notification.persistence.entity.NotificationEntity;
import team.retum.jobis.domain.notification.persistence.repository.NotificationJpaRepository;

import java.util.List;

@RequiredArgsConstructor
@Component
public class NoticeMapper {

    public NoticeEntity toEntity(Notice domain) {
        List<NoticeAttachmentEntity> attachments = domain.getAttachments().stream()
            .map(attachment -> new NoticeAttachmentEntity(attachment.getUrl(), attachment.getType()))
            .toList();

        return NoticeEntity.builder()
            .id(domain.getId())
            .title(domain.getTitle())
            .content(domain.getContent())
            .attachments(attachments)
            .build();
    }

    public Notice toDomain(NoticeEntity entity) {
        List<NoticeAttachment> attachments = entity.getAttachments().stream()
            .map(attachment -> new NoticeAttachment(attachment.getAttachmentUrl(), attachment.getType()))
            .toList();

        return Notice.builder()
            .id(entity.getId())
            .title(entity.getTitle())
            .content(entity.getContent())
            .createdAt(entity.getCreatedAt())
            .attachments(attachments)
            .build();
    }
}
