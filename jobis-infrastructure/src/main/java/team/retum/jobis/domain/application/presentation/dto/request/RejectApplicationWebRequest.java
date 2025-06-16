package team.retum.jobis.domain.application.presentation.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import team.retum.jobis.domain.application.dto.request.RejectionAttachmentRequest;
import team.retum.jobis.global.annotation.ValidListElements;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Getter
@NoArgsConstructor
public class RejectApplicationWebRequest {

    @NotBlank
    @Size(min = 1, max = 100)
    private String reason;

    @ValidListElements
    @NotNull
    private List<RejectionAttachmentWebRequest> rejectionAttachments;

    @Getter
    @NoArgsConstructor
    public static class RejectionAttachmentWebRequest {

        private String url;

    }

    public List<RejectionAttachmentRequest> toDomainAttachmentRequest() {
        if (rejectionAttachments.isEmpty()) {
            return Collections.emptyList();
        } else {
            return rejectionAttachments.stream()
                .map(rejectionAttachment -> {
                    return RejectionAttachmentRequest.builder()
                        .attachmentUrl(rejectionAttachment.getUrl())
                        .build();
                }).toList();
        }
    }
}
