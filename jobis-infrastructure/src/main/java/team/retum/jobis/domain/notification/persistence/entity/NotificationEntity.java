package team.retum.jobis.domain.notification.persistence.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import team.retum.jobis.domain.auth.model.Authority;
import team.retum.jobis.domain.notification.model.Topic;
import team.retum.jobis.domain.user.persistence.entity.UserEntity;
import team.retum.jobis.global.entity.BaseTimeEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "tbl_notification")
@Entity
public class NotificationEntity extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(columnDefinition = "VARCHAR(20)")
    private String title;

    @NotNull
    @Column(columnDefinition = "VARCHAR(300)")
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity userEntity;

    @NotNull
    @Column(columnDefinition = "BIGINT")
    private Long detailId;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "VARCHAR(26)")
    private Topic topic;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "VARCHAR(9)")
    private Authority authority;

    @NotNull
    @Column(columnDefinition = "TINYINT(1)")
    private boolean isNew;

    @Builder
    public NotificationEntity(Long id, String title, String content, UserEntity userEntity, Long detailId, Topic topic, Authority authority, boolean isNew) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.userEntity = userEntity;
        this.detailId = detailId;
        this.topic = topic;
        this.authority = authority;
        this.isNew = isNew;
    }
}
