package team.retum.jobis.domain.application.presentation.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import team.retum.jobis.domain.application.dto.request.CreateApplicationRequest;
import team.retum.jobis.domain.application.dto.request.CreateApplicationRequest.AttachmentRequest;
import team.retum.jobis.domain.application.model.AttachmentType;
import team.retum.jobis.global.annotation.ValidListElements;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.List;

@Getter
@NoArgsConstructor
public class CreateApplicationWebRequest {

    @ValidListElements
    private List<AttachmentWebRequest> attachments;

    public CreateApplicationRequest toDomainRequest() {
        return CreateApplicationRequest.builder()
                .attachments(
                        this.attachments.stream()
                                .map(
                                        attachment -> AttachmentRequest.builder()
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
