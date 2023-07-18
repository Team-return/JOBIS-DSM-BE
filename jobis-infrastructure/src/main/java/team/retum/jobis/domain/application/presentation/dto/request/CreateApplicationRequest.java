package team.retum.jobis.domain.application.presentation.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import team.retum.jobis.domain.application.domain.enums.AttachmentType;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@NoArgsConstructor
public class CreateApplicationRequest {

    @Valid
    private List<AttachmentRequest> attachments;

    @Getter
    public static class AttachmentRequest {

        @NotBlank
        private String url;

        @NotNull
        private AttachmentType type;
    }
}
