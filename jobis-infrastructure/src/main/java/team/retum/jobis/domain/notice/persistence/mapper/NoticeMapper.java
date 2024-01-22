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

    private final NotificationJpaRepository notificationJpaRepository;

    public NoticeEntity toEntity(Notice domain) {
        NotificationEntity notification = notificationJpaRepository.findById(domain.getNotificationId())
                .orElseThrow(() -> NotificationNotFoundException.EXCEPTION);

        List<NoticeAttachmentEntity> attachments = domain.getAttachments().stream()
                .map(attachment -> new NoticeAttachmentEntity(attachment.getAttachmentUrl()))
                .toList();

        return NoticeEntity.builder()
                .id(domain.getId())
                .title(domain.getTitle())
                .content(domain.getContent())
                .notificationEntity(notification)
                .createdAt(domain.getCreatedAt())
                .attachments(attachments)
                .build();
    }

    public Notice toDomain(NoticeEntity entity) {
        List<NoticeAttachment> attachments = entity.getAttachments().stream()
                .map(attachment -> new NoticeAttachment(attachment.getAttachmentUrl()))
                .toList();

        return Notice.builder()
                .id(entity.getId())
                .title(entity.getTitle())
                .content(entity.getContent())
                .notificationId(entity.getNotification().getId())
                .createdAt(entity.getCreatedAt())
                .attachments(attachments)
                .build();
    }
}