package team.retum.jobis.domain.bookmark.model;

import team.retum.jobis.common.annotation.Aggregate;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@Aggregate
public class Bookmark {

    private final Long recruitmentId;

    private final Long studentId;
}
