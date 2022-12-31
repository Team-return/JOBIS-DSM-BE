package com.example.jobis.domain.code.controller.dto.response;

import com.example.jobis.domain.code.domain.enums.JobType;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class JobCodeResponse {
    private final Long code;
    private final String keyword;
    private final JobType jobType;
}
