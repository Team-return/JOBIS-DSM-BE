package team.retum.jobis.domain.application.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import team.retum.jobis.domain.application.model.ApplicationRejectionAttachment;

import java.util.List;

@Getter
@NoArgsConstructor(force = true)
@AllArgsConstructor
public class QueryRejectionReasonResponse {

    private final String rejectionReason;

    private final List<ApplicationRejectionAttachment> rejectionAttachments;
}
