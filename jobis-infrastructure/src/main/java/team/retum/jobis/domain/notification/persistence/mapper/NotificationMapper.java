package team.retum.jobis.domain.notification.persistence.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import team.retum.jobis.domain.notification.model.Notification;
import team.retum.jobis.domain.notification.persistence.entity.NotificationEntity;
import team.retum.jobis.domain.user.persistence.entity.UserEntity;
import team.retum.jobis.domain.user.persistence.repository.UserJpaRepository;

@RequiredArgsConstructor
@Component
public class NotificationMapper {

    private final UserJpaRepository userJpaRepository;

    public NotificationEntity toEntity(Notification notification) {
        UserEntity userEntity = userJpaRepository.getReferenceById(notification.getUserId());

        return NotificationEntity.builder()
            .id(notification.getId())
            .title(notification.getTitle())
            .content(notification.getContent())
            .userEntity(userEntity)
            .detailId(notification.getDetailId())
            .topic(notification.getTopic())
            .authority(notification.getAuthority())
            .isNew(notification.getIsNew())
            .build();
    }

    public Notification toDomain(NotificationEntity notificationEntity) {
        return Notification.builder()
            .id(notificationEntity.getId())
            .title(notificationEntity.getTitle())
            .content(notificationEntity.getContent())
            .userId(notificationEntity.getUserEntity().getId())
            .detailId(notificationEntity.getDetailId())
            .topic(notificationEntity.getTopic())
            .authority(notificationEntity.getAuthority())
            .isNew(notificationEntity.isNew())
            .createdAt(notificationEntity.getCreatedAt())
            .build();
    }
}
