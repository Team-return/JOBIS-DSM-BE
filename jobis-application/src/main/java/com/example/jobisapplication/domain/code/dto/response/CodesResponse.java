package com.example.jobisapplication.domain.code.dto.response;

import com.example.jobisapplication.domain.code.model.Code;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import team.retum.jobis.domain.code.persistence.entity.CodeEntity;
import com.example.jobisapplication.domain.code.model.JobType;

import java.util.List;

@Getter
@AllArgsConstructor
public class CodesResponse {

    private final List<CodeResponse> codes;

    public static CodeResponse of(Code code) {
        return CodeResponse.builder()
                .code(code.getId())
                .keyword(code.getKeyword())
                .jobType(code.getJobType())
                .build();
    }

    @Getter
    @Builder
    public static class CodeResponse {

        private final Long code;
        private final String keyword;
        private final JobType jobType;
    }
}
