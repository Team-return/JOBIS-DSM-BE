package com.example.jobisapplication.domain.review.model;

import com.example.jobisapplication.common.annotation.Aggregate;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@Aggregate
public class QnAElement {

    private final String question;

    private final String answer;

    private final Long codeId;
}
