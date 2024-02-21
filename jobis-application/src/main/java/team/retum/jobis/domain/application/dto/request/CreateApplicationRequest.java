package team.retum.jobis.domain.application.dto.request;

import java.util.List;

public record CreateApplicationRequest(
        List<AttachmentRequest> attachmentRequests
) {}

