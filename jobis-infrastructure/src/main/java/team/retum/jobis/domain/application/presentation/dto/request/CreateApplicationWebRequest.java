package team.retum.jobis.domain.application.presentation.dto.request;

import team.retum.jobis.domain.application.dto.request.CreateApplicationRequest;
import team.retum.jobis.domain.application.dto.request.CreateApplicationRequest.AttachmentRequest;
import lombok.Getter;
import lombok.NoArgsConstructor;
import team.retum.jobis.domain.application.model.AttachmentType;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@NoArgsConstructor
public class CreateApplicationWebRequest {

    @Valid
    private List<AttachmentWebRequest> attachments;

    @Getter
    public static class AttachmentWebRequest {

        @NotBlank
        private String url;

        @NotNull
        private AttachmentType type;
    }

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
}
