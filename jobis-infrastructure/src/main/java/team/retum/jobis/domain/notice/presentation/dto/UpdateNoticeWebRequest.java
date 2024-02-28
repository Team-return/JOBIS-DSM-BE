package team.retum.jobis.domain.notice.presentation.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import team.retum.jobis.domain.notice.dto.request.UpdateNoticeRequest;

@Getter
@NoArgsConstructor
public class UpdateNoticeWebRequest {

    @NotBlank
    private String title;

    @NotBlank
    private String content;

    public UpdateNoticeRequest toDomainRequest() {
        return new UpdateNoticeRequest(
                this.title,
                this.content
        );
    }
}