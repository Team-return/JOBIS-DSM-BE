package team.retum.jobis.domain.application.dto.request;

import team.retum.jobis.domain.application.model.AttachmentType;

public record AttachmentRequest(
        String url,
        AttachmentType type
) {}
