package team.retum.jobis.domain.notice.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import team.retum.jobis.domain.notice.model.Notice;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class NoticePostedEvent {

    private Notice notice;
}
