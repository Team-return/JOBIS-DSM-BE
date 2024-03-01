package team.retum.jobis.domain.notification.model;

import lombok.Builder;
import lombok.Getter;
import team.retum.jobis.common.annotation.Aggregate;
import team.retum.jobis.domain.auth.model.Authority;

import java.time.LocalDateTime;

@Getter
@Builder(toBuilder = true)
@Aggregate
public class Notification {

    private final Long id;
    private final String title;
    private final String content;
    private final Long userId;
    private final Topic topic;
    private final Long detailId;
    private final Authority authority;
    private final Boolean isNew;
    private final LocalDateTime createdAt;

    public Notification read() {
        return this.toBuilder()
                .isNew(false)
                .build();
    }
}