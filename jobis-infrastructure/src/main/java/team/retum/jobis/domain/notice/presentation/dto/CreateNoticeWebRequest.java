package team.retum.jobis.domain.notice.presentation.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import team.retum.jobis.domain.notice.dto.request.CreateNoticeRequest;
import team.retum.jobis.domain.notice.model.AttachmentType;
import team.retum.jobis.global.annotation.ValidListElements;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
public class CreateNoticeWebRequest {

    @NotBlank
    private String title;

    @NotBlank
    private String content;

    @ValidListElements
    private List<AttachmentWebRequest> attachments;

    public CreateNoticeRequest toDomainRequest() {
        List<CreateNoticeRequest.AttachmentRequest> attachmentRequests = this.attachments.stream()
            .map(attachment -> new CreateNoticeRequest.AttachmentRequest(attachment.url, attachment.type))
            .collect(Collectors.toList());

        return new CreateNoticeRequest(this.title, this.content, attachmentRequests);
    }

    @Getter
    public static class AttachmentWebRequest {

        @NotBlank
        private String url;

        @NotNull
        private AttachmentType type;
    }
}
