package team.retum.jobis.domain.application.presentation.dto.request;


import jakarta.persistence.ElementCollection;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import team.retum.jobis.global.annotation.ValidListElements;

import java.util.List;

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
}
