package com.example.jobis.domain.code.controller.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CodeResponse {
    private final Long code;
    private final String keyword;
}
