package team.retum.jobis.domain.notice.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import team.retum.jobis.domain.notice.spi.vo.NoticeVO;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@AllArgsConstructor
public class QueryNoticesResponse {

    private final List<QueryNoticeResponse> notices;

    public static QueryNoticesResponse from(List<NoticeVO> noticeVOs) {
        return new QueryNoticesResponse(
                noticeVOs.stream()
                        .map(notice -> QueryNoticeResponse.builder()
                                .noticeId(notice.getId())
                                .title(notice.getTitle())
                                .createAt(notice.getCreatedAt())
                                .build()
                        ).toList()
        );
    }

    @Getter
    @Builder
    public static class QueryNoticeResponse {
        private final Long noticeId;
        private final String title;
        private final LocalDateTime createAt;
    }
}