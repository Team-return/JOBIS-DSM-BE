package team.retum.jobis.domain.notification.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import team.retum.jobis.domain.notification.model.Topic;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@IdClass(TopicSubscriptionId.class)
@Table(name = "tbl_topic_subscription")
@Entity
public class TopicSubscriptionEntity {

    @Id
    @NotNull
    private String deviceToken;

    @Id
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "VARCHAR(90)")
    private Topic topic;

    @NotNull
    @Column(columnDefinition = "TINYINT(1)")
    private boolean isSubscribed;

    @Builder
    public TopicSubscriptionEntity(String deviceToken, Topic topic, boolean isSubscribed) {
        this.deviceToken = deviceToken;
        this.topic = topic;
        this.isSubscribed = isSubscribed;
    }
}
