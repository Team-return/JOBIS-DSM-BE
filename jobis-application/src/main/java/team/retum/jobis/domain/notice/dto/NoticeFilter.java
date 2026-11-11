package team.retum.jobis.domain.notice.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class NoticeFilter {

    private final Long page;

    @Builder.Default
    private int limit = 10;

    public Long getOffset() {
        return (page - 1) * limit;
    }
}
