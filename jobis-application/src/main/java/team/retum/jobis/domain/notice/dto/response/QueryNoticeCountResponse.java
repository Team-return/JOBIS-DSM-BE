package team.retum.jobis.domain.notice.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class QueryNoticeCountResponse {

    private final int totalPageCount;

    private final long totalCount;
}
