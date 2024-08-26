package team.retum.jobis.domain.notification.persistence.entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import team.retum.jobis.domain.notification.model.Topic;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode
public class TopicSubscriptionId implements Serializable {

    private String deviceToken;
    private Topic topic;
}
