package team.retum.jobis.domain.intern.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import team.retum.jobis.domain.notification.model.Topic;
import team.retum.jobis.domain.recruitment.model.Recruitment;

import java.util.List;

@Getter
@AllArgsConstructor
public class WinterInternRegisteredEvent {

    private final Recruitment recruitment;
    private final Long detailId;
    private final Topic topic;
    private final List<String> deviceTokens;
}
