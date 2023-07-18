package team.retum.jobis.domain.application.presentation.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import team.retum.jobis.domain.application.persistence.entity.ApplicationAttachmentEntity;
import com.example.jobisapplication.domain.application.model.AttachmentType;

@Getter
@AllArgsConstructor
public class AttachmentResponse {
    private String url;

    private AttachmentType type;

    public static AttachmentResponse of(ApplicationAttachmentEntity applicationAttachmentEntity) {
        return new AttachmentResponse(
                applicationAttachmentEntity.getAttachmentUrl(),
                applicationAttachmentEntity.getType()
        );
    }
}
