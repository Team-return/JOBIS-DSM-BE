package com.example.jobisapplication.domain.application.domain;

import com.example.jobisapplication.common.annotation.Aggregate;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@Aggregate
public class ApplicationAttachment {

    private final String attachmentUrl;

    private final AttachmentType type;

    private final Long applicationId;
}
