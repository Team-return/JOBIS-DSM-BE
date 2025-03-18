package team.retum.jobis.domain.notice.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import team.retum.jobis.domain.notice.model.Notice;
import team.retum.jobis.domain.notification.model.Topic;

import java.util.List;

@Getter
@AllArgsConstructor
public class NoticePostedEvent {

    private final Notice notice;
    private final Long detailId;
    private final Topic topic;
    private final List<String> deviceTokens;
}
