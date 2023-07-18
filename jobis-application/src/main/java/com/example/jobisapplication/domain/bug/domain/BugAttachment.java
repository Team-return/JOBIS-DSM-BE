package com.example.jobisapplication.domain.bug.domain;

import com.example.jobisapplication.common.annotation.Aggregate;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@Aggregate
public class BugAttachment {

    private final Long bugReportId;

    private final String attachmentUrl;
}
