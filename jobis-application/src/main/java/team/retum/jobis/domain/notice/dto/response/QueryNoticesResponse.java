package team.retum.jobis.domain.notice.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

@Getter
@AllArgsConstructor
public class QueryNoticesResponse {

    private final List<QueryNoticeResponse> notices;

    @Getter
    @Builder
    public static class QueryNoticeResponse {

        private final Long noticeId;
        private final String title;
        private final String content;
        private final List<AttachmentResponse> attachments;
        private final LocalDate createdAt;
    }
}
