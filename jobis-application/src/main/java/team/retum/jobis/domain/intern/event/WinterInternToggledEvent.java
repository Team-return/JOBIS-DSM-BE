package team.retum.jobis.domain.intern.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import team.retum.jobis.domain.intern.model.WinterIntern;
import team.retum.jobis.domain.notification.model.Topic;

import java.util.List;

@Getter
@AllArgsConstructor
public class WinterInternToggledEvent {

    private final WinterIntern winterIntern;
    private final Long detailId;
    private final Topic topic;
    private final List<String> deviceTokens;
}
