package com.example.jobis.domain.application.controller.dto.response;

import com.querydsl.core.annotations.QueryProjection;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
public class QueryApplicationDetailsResponse {

    private final List<String> ApplicationAttachmentUrl;

    @QueryProjection
    public QueryApplicationDetailsResponse(List<String> applicationAttachmentUrl) {
        ApplicationAttachmentUrl = applicationAttachmentUrl;
    }
}
