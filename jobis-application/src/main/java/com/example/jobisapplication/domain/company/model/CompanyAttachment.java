package com.example.jobisapplication.domain.company.model;

import com.example.jobisapplication.common.annotation.Aggregate;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@Aggregate
public class CompanyAttachment {

    private final Long id;

    private final String attachmentUrl;

    private final Long companyId;
}
