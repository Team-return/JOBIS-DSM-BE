package team.retum.jobis.domain.notice.model;

import lombok.Builder;
import lombok.Getter;
import team.retum.jobis.common.annotation.Aggregate;

import java.time.LocalDateTime;

@Aggregate
@Builder(toBuilder = true)
@Getter
public class ViewLog {

    private final Long id;

    private final Long noticeId;

    private final Long studentId;

    private final LocalDateTime viewedAt;
}
