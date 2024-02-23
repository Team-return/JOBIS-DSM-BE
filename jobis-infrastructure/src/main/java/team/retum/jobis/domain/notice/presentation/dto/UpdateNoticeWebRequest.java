package team.retum.jobis.domain.notice.presentation.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import team.retum.jobis.domain.notice.dto.request.UpdateNoticeRequest;

@Getter
@NoArgsConstructor
public class UpdateNoticeWebRequest {

    @NotNull
    private String title;

    @NotNull
    private String content;

    public UpdateNoticeRequest toDomainRequest() {
        return new UpdateNoticeRequest(
                this.title,
                this.content
        );
    }
}
