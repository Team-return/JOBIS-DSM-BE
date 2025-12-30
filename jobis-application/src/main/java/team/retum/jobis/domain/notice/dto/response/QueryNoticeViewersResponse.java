package team.retum.jobis.domain.notice.dto.response;

import lombok.Builder;
import lombok.Getter;
import team.retum.jobis.domain.notice.spi.vo.ViewerVO;

import java.util.List;

@Getter
@Builder
public class QueryNoticeViewersResponse {

    private final List<ViewerResponse> viewers;

    public static QueryNoticeViewersResponse of(List<ViewerVO> viewers) {
        return QueryNoticeViewersResponse.builder()
            .viewers(
                viewers.stream()
                    .map(ViewerResponse::from)
                    .toList()
            )
            .build();
    }
}
