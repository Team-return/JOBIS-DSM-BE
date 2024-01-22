package team.retum.jobis.domain.notice.persistence.entity;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import team.retum.jobis.domain.notification.persistence.entity.NotificationEntity;
import team.retum.jobis.global.entity.BaseTimeEntity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "tbl_notice")
public class NoticeEntity extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(columnDefinition = "VARCHAR(50)")
    private String title;

    @NotNull
    @Column(columnDefinition = "VARCHAR(1000)")
    private String content;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "notification_id", nullable = false)
    private NotificationEntity notification;

    @NotNull
    @Column(columnDefinition = "DATE")
    private LocalDateTime createdAt;

    @ElementCollection
    @CollectionTable(name = "tbl_notice_attachment", joinColumns = @JoinColumn(name = "notice_id"))
    private List<NoticeAttachmentEntity> attachments = new ArrayList<>();


    @Builder
    public NoticeEntity(Long id, String title, String content, NotificationEntity notificationEntity,
                        LocalDateTime createdAt, List<NoticeAttachmentEntity> attachments) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.notification = notificationEntity;
        this.createdAt = createdAt;
        this.attachments = attachments;
    }

}
