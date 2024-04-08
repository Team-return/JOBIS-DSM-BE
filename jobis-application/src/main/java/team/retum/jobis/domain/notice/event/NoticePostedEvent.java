package team.retum.jobis.domain.notice.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import team.retum.jobis.domain.notice.model.Notice;

@Getter
@AllArgsConstructor
public class NoticePostedEvent {

    private final Notice notice;
}
