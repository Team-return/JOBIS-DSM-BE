package team.retum.jobis.domain.notice.dto.request;

import lombok.Getter;

@Getter
public class UpdateNoticeRequest {

    private String title;

    private String content;

    public UpdateNoticeRequest(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
