package team.retum.jobis.domain.application.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import team.retum.jobis.domain.application.dto.request.RejectionAttachmentRequest;

import java.util.List;

@Getter
@AllArgsConstructor
public class ApplicationRejectionAttachment {

    private String attachmentUrl;

    public static List<ApplicationRejectionAttachment> from(List<RejectionAttachmentRequest> requests) {
        return requests.stream()
                .map(request -> new ApplicationRejectionAttachment(request.attachmentUrl()))
                .toList();
    }
}
