package team.retum.jobis.domain.notice.presentation.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import team.retum.jobis.domain.notice.dto.request.CreateNoticeRequest;
import team.retum.jobis.domain.notice.model.AttachmentType;
import team.retum.jobis.global.annotation.ValidListElements;

import java.util.List;

@Getter
@NoArgsConstructor
public class CreateNoticeWebRequest {

    @NotNull
    private String title;

    @NotNull
    private String content;

    @ValidListElements
    private List<AttachmentWebRequest> attachments;

    public CreateNoticeRequest toDomainRequest() {
        return CreateNoticeRequest.builder()
                .title(this.title)
                .content(this.content)
                .attachments(
                        this.attachments.stream()
                                .map(
                                        attachment -> CreateNoticeRequest.AttachmentRequest.builder()
                                                .url(attachment.url)
                                                .type(attachment.type)
                                                .build()
                                ).toList()
                )
                .build();
    }

    @Getter
    public static class AttachmentWebRequest {

        @NotBlank
        private String url;

        @NotNull
        private AttachmentType type;
    }
}
