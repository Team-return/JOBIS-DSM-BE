package team.retum.jobis.domain.bookmark.model;

import lombok.Builder;
import lombok.Getter;
import team.retum.jobis.common.annotation.Aggregate;

@Getter
@Builder
@Aggregate
public class Bookmark {

    private final Long recruitmentId;

    private final Long studentId;
}
