package team.retum.jobis.domain.notice.presentation.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import team.retum.jobis.domain.notice.dto.request.CreateNoticeRequest;
import team.retum.jobis.domain.notice.model.AttachmentType;

import java.util.List;

@Getter
@NoArgsConstructor
public class CreateNoticeWebRequest {

    @NotBlank
    private String title;

    @NotBlank
    private String content;

    private List<@NotNull AttachmentWebRequest> attachments;

    public CreateNoticeRequest toDomainRequest() {
        List<CreateNoticeRequest.AttachmentRequest> attachmentRequests = this.attachments == null
            ? List.of()
            : this.attachments.stream()
                .map(attachment -> new CreateNoticeRequest.AttachmentRequest(attachment.url, attachment.type))
                .toList();

        return new CreateNoticeRequest(this.title, this.content, attachmentRequests);
    }

    @Getter
    public static class AttachmentWebRequest {

        private String url;

        private AttachmentType type;
    }
}
