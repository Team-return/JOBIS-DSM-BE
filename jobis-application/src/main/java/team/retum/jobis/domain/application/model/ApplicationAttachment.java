package team.retum.jobis.domain.application.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import team.retum.jobis.domain.application.dto.request.AttachmentRequest;

import java.util.List;

@Getter
@AllArgsConstructor
public class ApplicationAttachment {

    private final String attachmentUrl;

    private final AttachmentType type;

    public static List<ApplicationAttachment> from(List<AttachmentRequest> requests) {
        return requests.stream()
            .map(request -> new ApplicationAttachment(request.url(), request.type()))
            .toList();
    }
}
