package team.retum.jobis.domain.application.presentation.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import team.retum.jobis.domain.application.model.AttachmentType;
import team.retum.jobis.global.annotation.ValidListElements;

import java.util.List;

@Getter
@NoArgsConstructor
public class CreateApplicationWebRequest {

    @ValidListElements
    private List<AttachmentWebRequest> attachments;

    @Getter
    public static class AttachmentWebRequest {

        @NotBlank
        private String url;

        @NotNull
        private AttachmentType type;
    }
}
